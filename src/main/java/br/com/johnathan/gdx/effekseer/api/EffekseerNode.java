/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package br.com.johnathan.gdx.effekseer.api;

public class EffekseerNode {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected EffekseerNode(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(EffekseerNode obj) {
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
        GDXJNI.delete_EffekseerNode(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public EffekseerNode(EffekseerNode effct, int index) {
    this(GDXJNI.new_EffekseerNode(EffekseerNode.getCPtr(effct), effct, index), true);
  }

  public int GetPositionType() {
    return GDXJNI.EffekseerNode_GetPositionType(swigCPtr, this);
  }

  public int GetScaleType() {
    return GDXJNI.EffekseerNode_GetScaleType(swigCPtr, this);
  }

  public int GetRotacionType() {
    return GDXJNI.EffekseerNode_GetRotacionType(swigCPtr, this);
  }

  public int getNodes() {
    return GDXJNI.EffekseerNode_getNodes(swigCPtr, this);
  }

  public EffekseerNode getNode(int index) {
    return new EffekseerNode(GDXJNI.EffekseerNode_getNode(swigCPtr, this, index), true);
  }

  public String GetName() {
    return GDXJNI.EffekseerNode_GetName(swigCPtr, this);
  }

  public void SetPosition(ParameterTranslationType type, float[] array) {
    GDXJNI.EffekseerNode_SetPosition(swigCPtr, this, type.swigValue(), array);
  }

  public VectorFloat GetPosition(ParameterTranslationType type) {
    return new VectorFloat(GDXJNI.EffekseerNode_GetPosition(swigCPtr, this, type.swigValue()), true);
  }

  public void SetScale(ParameterScalingType type, float[] array) {
    GDXJNI.EffekseerNode_SetScale(swigCPtr, this, type.swigValue(), array);
  }

  public VectorFloat GetScale(ParameterScalingType type) {
    return new VectorFloat(GDXJNI.EffekseerNode_GetScale(swigCPtr, this, type.swigValue()), true);
  }

  public void SetRotation(ParameterRotationType type, float[] array) {
    GDXJNI.EffekseerNode_SetRotation(swigCPtr, this, type.swigValue(), array);
  }

  public VectorFloat GetRotation(ParameterRotationType type) {
    return new VectorFloat(GDXJNI.EffekseerNode_GetRotation(swigCPtr, this, type.swigValue()), true);
  }

  public ParameterTranslationType TranslationType() {
    return ParameterTranslationType.swigToEnum(GDXJNI.EffekseerNode_TranslationType(swigCPtr, this));
  }

  public ParameterRotationType RotationType() {
    return ParameterRotationType.swigToEnum(GDXJNI.EffekseerNode_RotationType(swigCPtr, this));
  }

  public ParameterScalingType ScalingType() {
    return ParameterScalingType.swigToEnum(GDXJNI.EffekseerNode_ScalingType(swigCPtr, this));
  }

}
