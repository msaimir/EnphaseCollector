## Docker files Available

**Dockerfile**
This is the default file I use with my CI tool to generate the docker image AFTER a successful JAR build and test.  It takes the jar file as an argument.

**Dockerfile-arm**
This is the same as Dockerfile except it will build an ARM image.  Not all arm devices are supported here.

**Dockerfile-build**
This docker file can be used if you want to build from scratch without installing Java.  It will download the source files and everything needed to build the application and generate an image.

**Dockerfile-builder**
This docker file will download the jar file from Github and build an image.  It takes the version you want as an argument.

**Dockerfile-google**
Similar to the default Dockerfile but uses the Google base image for java support

**Dockerfile-slimmed**
This is an experimental Dockerfile where I look at building smaller images using various techniques.