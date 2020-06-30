package particles;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

public class EffekseerManager implements Disposable {

    protected EffekseerManagerCore effekseerManagerCore;
    protected CameraView cameraView;
    private Camera camera;
    private ArrayList<ParticleEffekseer> effekseers;



    public static void InitializeEffekseer(){

            try {
                System.loadLibrary("src");
            } catch (Error | Exception exception) {

            }
    }



    public EffekseerManager( Camera camera,  CameraView cameraView) {

        this.camera = camera;
        this.cameraView = cameraView;

        effekseers = new ArrayList<>();


        EffekseerBackendCore.InitializeAsOpenGL();
        effekseerManagerCore = new EffekseerManagerCore();



        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            effekseerManagerCore.Initialize(600, EffekseerCore.TypeOpenGL.OPEN_GLES2);

        } else {
            effekseerManagerCore.Initialize(2000, EffekseerCore.TypeOpenGL.OPEN_GL2);

        }



    }


    public void setCameraPosition(){



    }


    public void addParticleEffekseer(ParticleEffekseer effekseers ){
        this.effekseers.add(effekseers);

    }


    public void draw( float delta) {


        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
        Gdx.gl.glCullFace(GL20.GL_BACK);



        switch (cameraView) {

            case CAMERA_2VIEW:

                effekseerManagerCore.SetViewProjectionMatrixWithSimpleWindowOrthogonal( (int) camera.viewportWidth, (int) camera.viewportHeight);

                break;


            case CAMERA_3VIEW:

                effekseerManagerCore.SetViewProjectionMatrixWithSimpleWindowPerspective(camera.viewportWidth, camera.viewportHeight, (PerspectiveCamera) camera);
                effekseerManagerCore.SetCameraPosition(  ((PerspectiveCamera) camera).position.x  , (  (PerspectiveCamera) camera).position.y, (  (PerspectiveCamera) camera).position.z);
                effekseerManagerCore.SetCameraRotate(camera.direction.x,camera.direction.y,camera.direction.z);
                camera.update();

                break;

        }

        effekseerManagerCore.Update(delta / (1.0f / 60.0f));
        effekseerManagerCore.DrawBack();
        effekseerManagerCore.DrawFront();


        Gdx.gl.glCullFace(GL20.GL_FRONT);
        Gdx.gl.glDisable(GL20.GL_BLEND);



    }





    @Override
    public void dispose() {

        effekseerManagerCore.delete();
        for (ParticleEffekseer effekseer : this.effekseers){
            effekseer.delete();
        }
        EffekseerBackendCore.Terminate();

    }
}
