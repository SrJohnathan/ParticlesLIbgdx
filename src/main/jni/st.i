/* File: example.i */

%module GDX
%{

#include "lib/effekseernode.h"
#include "lib/EffekseerBackendCore.h"
#include "lib/EffekseerEffectCore.h"
#include "lib/EffekseerManagerCore.h"


%}


%include "stdint.i"

%include "typemaps.i"
%include "./char16.i"
%include "various.i"
%include "arrays_java.i"
%include "std_vector.i"

%include "carrays.i"

%apply float[] {float *};


%apply char *BYTE { char* data };


%include "./ibyte.i"
%apply unsigned char *UBYTE { unsigned char *data };


namespace std {
    %template(VectorFloat) vector<float>;
};






%include "F:/Gdx-Effekseer/src/lib/effekseernode.h"
%include "F:/Gdx-Effekseer/src/lib/EffekseerBackendCore.h"
%include "F:/Gdx-Effekseer/src/lib/EffekseerEffectCore.h"
%include "F:/Gdx-Effekseer/src/lib/EffekseerManagerCore.h"







