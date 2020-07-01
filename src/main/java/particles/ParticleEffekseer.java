package particles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;

import java.io.File;


public class ParticleEffekseer {

    private EffekseerManager manager;
    private final EffekseerEffectCore effekseerEffectCore;
    private OnAnimationComplete onAnimationComplete;
    private int handle;
    private boolean play;
    private float magnification = 1.0f;
    private float X= 0f, Y = 0f, Z = 0f;


    public ParticleEffekseer(EffekseerManager manager) {
        this.manager = manager;
        this.manager.addParticleEffekseer(this);
        effekseerEffectCore = new EffekseerEffectCore();
    }

    public void setMagnification(float magnification) {
        this.magnification = magnification;
    }


    public boolean isPlay() {
        return play;
    }

    protected void setPlay(boolean play) {
        this.play = play;
    }

    protected void delete() {
        effekseerEffectCore.delete();
    }


    public void setLacation(float x, float y, float z) {

        X = x;
        Y = y;
        Z = z;


        if(manager.camera instanceof PerspectiveCamera){
            manager.effekseerManagerCore.SetEffectPosition(handle, x, y, z);
        }

        if(manager.camera instanceof OrthographicCamera){
            manager.effekseerManagerCore.SetEffectPosition(handle, (-(manager.camera.viewportWidth /2) +X), (-(manager.camera.viewportHeight  /2) +Y), 0);


        }

    }

    public int getHandle() {
        return handle;
    }

    public void play() {
        play = true;
        handle = manager.effekseerManagerCore.Play(effekseerEffectCore);
        setLacation(X,Y,Z);
    }


    public void load(String path, boolean internalStorage) throws Exception {


        FileHandle handle = null;
        if (internalStorage) {
            handle = Gdx.files.internal(path);
        } else {
            handle = Gdx.files.external(path);
        }

        byte[] byt = handle.readBytes();


        if (manager.effekseerManagerCore == null) {
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


                FileHandle handle1 = null;
                if (internalStorage) {
                    handle1 = Gdx.files.internal(pathh);
                } else {
                    handle1 = Gdx.files.external(pathh);
                }


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


            FileHandle handle1 = null;
            if (internalStorage) {
                handle1 = Gdx.files.internal(pathh);
            } else {
                handle1 = Gdx.files.external(pathh);
            }


            byte[] bytes = handle1.readBytes();

            effekseerEffectCore.LoadModel(bytes, bytes.length, i);

        }

        for (int i = 0; i < effekseerEffectCore.GetMaterialCount(); i++) {
            String pathh = (new File(path)).getParent();
            if (pathh != null) {
                pathh += "/" + effekseerEffectCore.GetMaterialPath(i);
            } else {
                pathh = effekseerEffectCore.GetMaterialPath(i);
            }


            FileHandle handle1 = null;
            if (internalStorage) {
                handle1 = Gdx.files.internal(pathh);
            } else {
                handle1 = Gdx.files.external(pathh);
            }


            byte[] bytes = handle1.readBytes();
            effekseerEffectCore.LoadMaterial(bytes, bytes.length, i);
        }

        // TODO sound


    }

    public void setX(float x) {
        X = x;
    }

    public void setY(float y) {
        Y = y;
    }

    public void setZ(float z) {
        Z = z;
    }

    public float getX() {
        return X;
    }

    public float getY() {
        return Y;
    }

    public float getZ() {
        return Z;
    }


    public void  pause(){
        manager.effekseerManagerCore.pause(handle);
    }

    public void  resume(){
        manager.effekseerManagerCore.resume(handle);
    }

    protected OnAnimationComplete getOnAnimationComplete() {
        return onAnimationComplete;
    }

    public void setOnAnimationComplete(OnAnimationComplete onAnimationComplete) {
        this.onAnimationComplete = onAnimationComplete;
    }
}
