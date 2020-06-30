package particles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.File;


public class ParticleEffekseer {

    private EffekseerManager manager;
    private final EffekseerEffectCore effekseerEffectCore;
    private  int handle;
    private float magnification = 1.0f;
    public float X,Y,Z;





    public ParticleEffekseer(EffekseerManager manager) {
        this.manager = manager;
        this.manager.addParticleEffekseer(this);
        effekseerEffectCore = new EffekseerEffectCore();
    }




    public void setMagnification( float magnification) {
        this.magnification = magnification;
    }


    protected void delete() {
        effekseerEffectCore.delete();
    }


    public void setLacation( float x, float y,  float z) {

        X = x ;
        Y = y;
        Z = z;


        switch (manager.cameraView) {

            case CAMERA_2VIEW:

                manager.effekseerManagerCore.SetEffectPosition(handle,x,y,0);

                break;


            case CAMERA_3VIEW:

                manager.effekseerManagerCore.SetEffectPosition(handle,x,y,z);

                break;

        }

    }

    public void play(){

        handle = manager.effekseerManagerCore.Play(effekseerEffectCore);
    }


    public void load(String path) throws Exception {

        FileHandle handle = Gdx.files.external(path);


        byte[] byt = handle.readBytes();


                if(manager.effekseerManagerCore == null){
                  throw new Exception("add particle on manager");
                }



        try {
            if (!effekseerEffectCore.Load(byt, byt.length, magnification, manager.effekseerManagerCore)) {
                System.out.print("Failed to load.");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        // load textures
        EffekseerTextureType[] textureTypes = new EffekseerTextureType[]{
                EffekseerTextureType.Color,
                EffekseerTextureType.Normal,
                EffekseerTextureType.Distortion,
        };

        for (int t = 0; t < 3; t++) {
            for (int i = 0; i < effekseerEffectCore.GetTextureCount(textureTypes[t]); i++) {

                String pathh = null;
                pathh = (new File(path)).getParent();


                if (pathh != null) {
                    pathh += "/" + effekseerEffectCore.GetTexturePath(i, textureTypes[t]);
                } else {
                    pathh = effekseerEffectCore.GetTexturePath(i, textureTypes[t]);
                }

                FileHandle handle1 = Gdx.files.external(pathh);


                byte[] bytes = handle1.readBytes();


                effekseerEffectCore.LoadTexture(bytes, bytes.length, i, textureTypes[t]);
            }
        }

        for (int i = 0; i < effekseerEffectCore.GetModelCount(); i++) {
            String pathh = (new File(path)).getParent();

            if (pathh != null) {
                pathh += "/" + effekseerEffectCore.GetModelPath(i);
            } else {
                pathh = effekseerEffectCore.GetModelPath(i);
            }

            handle = Gdx.files.internal(pathh);
            byte[] bytes = handle.readBytes();

            System.out.println("Model " + effekseerEffectCore.LoadModel(bytes, bytes.length, i));

        }

        for (int i = 0; i < effekseerEffectCore.GetMaterialCount(); i++) {
            String pathh = (new File(path)).getParent();
            if (pathh != null) {
                pathh += "/" + effekseerEffectCore.GetMaterialPath(i);
            } else {
                pathh = effekseerEffectCore.GetMaterialPath(i);
            }

            System.out.println(path);

            handle = Gdx.files.internal(pathh);
            byte[] bytes = handle.readBytes();
            System.out.println("Material  " + effekseerEffectCore.LoadMaterial(bytes, bytes.length, i));
        }

        // TODO sound


    }


}
