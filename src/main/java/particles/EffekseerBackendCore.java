

package particles;

 class EffekseerBackendCore {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected EffekseerBackendCore(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(EffekseerBackendCore obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  @SuppressWarnings("deprecation")
  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        EffekseerCoreJNI.delete_EffekseerBackendCore(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public EffekseerBackendCore() {
    this(EffekseerCoreJNI.new_EffekseerBackendCore(), true);
  }

  public static void Zoom(int zoom) {
   // EffekseerCoreJNI.EffekseerBackendCore_Zoom(zoom);
  }

  public static EffekseerCoreDeviceType GetDevice() {
    return EffekseerCoreDeviceType.swigToEnum(EffekseerCoreJNI.EffekseerBackendCore_GetDevice());
  }

  public static boolean InitializeAsOpenGL() {
    return EffekseerCoreJNI.EffekseerBackendCore_InitializeAsOpenGL();
  }

  public static void Terminate() {
    EffekseerCoreJNI.EffekseerBackendCore_Terminate();
  }

}
