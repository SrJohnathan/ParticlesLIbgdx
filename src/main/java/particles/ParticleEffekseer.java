package particles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;

import java.io.File;


public class ParticleEffekseer {

    private EffekseerManager manager;
    private final EffekseerEffectCore effekseerEffectCore;
    private OnAnimationComplete onAnimationComplete;
    private int handle;
    private boolean play;
    private float magnification = 1.0f;
    private Vector3 position,rotate,scale;



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


    public void setRotate(Vector3 rotate) {
        this.rotate = rotate;
        manager.effekseerManagerCore.SetEffectRotate(handle, this.rotate.x, this.rotate.y, this.rotate.z);
    }

    public Vector3 getScale() {
        return scale;
    }

    public void setScale(Vector3 scale) {
        this.scale = scale;
        manager.effekseerManagerCore.SetEffectScale(handle, this.scale.x, this.scale.y, this.scale.z);

    }

    public void setPosition(Vector3 position) {

        this.position = position;

        if(manager.camera instanceof PerspectiveCamera){
            manager.effekseerManagerCore.SetEffectPosition(handle, this.position.x, this.position.y, this.position.z);
        }

        if(manager.camera instanceof OrthographicCamera){
            manager.effekseerManagerCore.SetEffectPosition(handle, (-(manager.camera.viewportWidth /2) +this.position.x), (-(manager.camera.viewportHeight  /2) +this.position.y), 0);


        }

    }

    public int getHandle() {
        return handle;
    }

    public void play() {
        play = true;
        handle = manager.effekseerManagerCore.Play(effekseerEffectCore);

        if(position == null){
            setPosition(new Vector3());
        }else {
            setPosition(position);
        }

        if(rotate != null){
            setRotate(rotate);
        }

        if(scale != null){
            setScale(scale);
        }

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

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getRotate() {
        return rotate;
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
