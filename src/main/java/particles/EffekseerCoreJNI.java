
package particles;

import java.io.IOException;

 class EffekseerCoreJNI {

    public final static native long new_EffekseerBackendCore();

    public final static native void delete_EffekseerBackendCore(long jarg1);

    public final static native int EffekseerBackendCore_GetDevice();

    public final static native boolean EffekseerBackendCore_InitializeAsOpenGL();

    public final static native void EffekseerBackendCore_Terminate();

    public final static native long new_EffekseerEffectCore();

    public final static native void delete_EffekseerEffectCore(long jarg1);

    public final static native boolean EffekseerEffectCore_Load(long jarg1, EffekseerEffectCore jarg1_, byte[] jarg2, int jarg3, float jarg4, long id);

    public final static native String EffekseerEffectCore_GetTexturePath(long jarg1, EffekseerEffectCore jarg1_, int jarg2, int jarg3);

    public final static native int EffekseerEffectCore_GetTextureCount(long jarg1, EffekseerEffectCore jarg1_, int jarg2);

    public final static native boolean EffekseerEffectCore_LoadTexture(long jarg1, EffekseerEffectCore jarg1_, byte[] jarg2, int jarg3, int jarg4, int jarg5);

    public final static native boolean EffekseerEffectCore_LoadTexturePath(long jarg1, EffekseerEffectCore jarg1_, String jarg2, int jarg3, int jarg4, int jarg5);

    public final static native boolean EffekseerEffectCore_HasTextureLoaded(long jarg1, EffekseerEffectCore jarg1_, int jarg2, int jarg3);

    public final static native String EffekseerEffectCore_GetModelPath(long jarg1, EffekseerEffectCore jarg1_, int jarg2);

    public final static native int EffekseerEffectCore_GetModelCount(long jarg1, EffekseerEffectCore jarg1_);

    public final static native boolean EffekseerEffectCore_LoadModel(long jarg1, EffekseerEffectCore jarg1_, byte[] jarg2, int jarg3, int jarg4);

    public final static native boolean EffekseerEffectCore_HasModelLoaded(long jarg1, EffekseerEffectCore jarg1_, int jarg2);

    public final static native String EffekseerEffectCore_GetMaterialPath(long jarg1, EffekseerEffectCore jarg1_, int jarg2);

    public final static native int EffekseerEffectCore_GetMaterialCount(long jarg1, EffekseerEffectCore jarg1_);

    public final static native boolean EffekseerEffectCore_LoadMaterial(long jarg1, EffekseerEffectCore jarg1_, byte[] jarg2, int jarg3, int jarg4);

    public final static native boolean EffekseerEffectCore_HasMaterialLoaded(long jarg1, EffekseerEffectCore jarg1_, int jarg2);


    public final static native long new_EffekseerManagerCore();

    public final static native void delete_EffekseerManagerCore(long jarg1);

    public final static native boolean EffekseerManagerCore_Initialize(long jarg1, EffekseerManagerCore jarg1_, int jarg2, int id);


    public final static native void EffekseerManagerCore_Update(long jarg1, EffekseerManagerCore jarg1_, float jarg2);

    public final static native void EffekseerManagerCore_Pause(long jarg1, EffekseerManagerCore jarg1_, int handle);
    public final static native void EffekseerManagerCore_Resume(long jarg1, EffekseerManagerCore jarg1_, int handle);

    public final static native boolean EffekseerManagerCore_Isplaying(long jarg1, EffekseerManagerCore jarg1_, int handle);



    public final static native void EffekseerManagerCore_DrawBack(long jarg1, EffekseerManagerCore jarg1_);

    public final static native void EffekseerManagerCore_DrawFront(long jarg1, EffekseerManagerCore jarg1_);


    public final static native void EffekseerManagerCore_SetProjectionMatrix(long jarg1, EffekseerManagerCore jarg1_, float[] projection, float[] camera,boolean view,float viewPortWidth,float viewPortHeiht);



    public final static native int EffekseerManagerCore_Play(long jarg1, EffekseerManagerCore jarg1_, long jarg2, EffekseerEffectCore jarg2_);


    public final static native void EffekseerManagerCore_SetEffectPosition(long jarg1, EffekseerManagerCore jarg1_, int jarg2, float jarg3, float jarg4, float jarg5);




 }