package particles;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

public class EffekseerManager implements Disposable {

    protected EffekseerManagerCore effekseerManagerCore;
    protected Camera camera;
    private ArrayList<ParticleEffekseer> effekseers;
    private Viewport viewport;



    public static void InitializeEffekseer(){

            try {
                System.loadLibrary("src");
            } catch (Error | Exception exception) {

                exception.printStackTrace();
            }
    }



    public EffekseerManager( Camera camera) {

        this.camera = camera;
        effekseers = new ArrayList<>();
        EffekseerBackendCore.InitializeAsOpenGL();
        effekseerManagerCore = new EffekseerManagerCore();

        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            effekseerManagerCore.Initialize(600, EffekseerCore.TypeOpenGL.OPEN_GLES2);

        } else {
            effekseerManagerCore.Initialize(2000, EffekseerCore.TypeOpenGL.OPEN_GL2);

        }



    }


    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    protected void addParticleEffekseer(ParticleEffekseer effekseers ){
        this.effekseers.add(effekseers);
    }


    public void draw( float delta) {
        camera.update();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
        Gdx.gl.glCullFace(GL20.GL_BACK);


        effekseers.forEach(effekseer -> {
            if(effekseer.isPlay()){
             if(!effekseerManagerCore.isPlaying(effekseer.getHandle())){
                    effekseer.setPlay(false);
                    if( effekseer.getOnAnimationComplete() != null){
                        effekseer.getOnAnimationComplete().finish();
                    }


                }
            }
        });


            if(camera instanceof OrthographicCamera){

                if(viewport != null){
                    effekseerManagerCore.setProjectionMatrix((camera).projection,(camera).view,false,viewport.getWorldWidth(),viewport.getWorldHeight());

                }else {
                    effekseerManagerCore.setProjectionMatrix((camera).projection,(camera).view,false,camera.viewportWidth,camera.viewportHeight);

                }


            }

            if (camera instanceof PerspectiveCamera){
                effekseerManagerCore.setProjectionMatrix((camera).projection,(camera).view,true,0,0);

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
