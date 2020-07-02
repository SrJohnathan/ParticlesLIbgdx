# LibGDX + Effekseer 


[![Bintray](https://img.shields.io/bintray/v/srjohnathan/gdx.effekseer/gdx.effekseer)](https://bintray.com/srjohnathan/gdx.effekseer/gdx.effekseer)
[ ![Download](https://api.bintray.com/packages/srjohnathan/gdx.effekseer/gdx.effekseer/images/download.svg) ](https://bintray.com/srjohnathan/gdx.effekseer/gdx.effekseer/_latestVersion)

Dedicated library for using Effekseer particle tools in libGDX


![Alt text](https://thumbs.gfycat.com/ThickDistinctDunnart-size_restricted.gif?raw=true "Title")

https://effekseer.github.io/en/

https://libgdx.badlogicgames.com/


### Native dependencies

You must download the native libraries

[download libs native](libs.zip)


```

Android Folder

libs 
    arm64-v8a
        libsrc.so
    armeabi-v7a
        libsrc.so
    x86
        libsrc.so

Windows Folder
        assets
            src.dll

```



### Core dependencies
```implementation 'br.com.johnathan.gdx.effekseer:api:0.0.3'```

sourceCompatibility = 1.8
### Starting

#### 3D Effects  perspectiveCamera

```
// Effekseer start
EffekseerManager.InitializeEffekseer();

  PerspectiveCamera  perspectiveCamera = new PerspectiveCamera(67, 1280f, 720);

  // Create a new manager for the particles
  EffekseerManager  manager = new EffekseerManager(perspectiveCamera);

        // create a new particle
        effekseer = new ParticleEffekseer(manager);
        effekseer.setMagnification(20f);
        try {
            
            // false = InternalStorage
            // true = ExternalStorage

            effekseer.load("data/tu.efk",false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    effekseer.play();
```


#### Render function

``` 
@Override
        public void render() {

        manager.draw(Gdx.graphics.getDeltaTime());
} 
```

#### dispose function

```  
@Override
    public void dispose() {
manager.dispose();
} 
```

#### 2D Effects  orthographicCamera
```
  OrthographicCamera  orthographicCamera = new OrthographicCamera(1280f,720f);
  EffekseerManager  manager = new EffekseerManager(orthographicCamera);
```

### Supported platforms

Android 

Windows

### Contributors:
* Antonio Johnathan       https://github.com/SrJohnathan
* Durswd                 https://github.com/durswd

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

