# TextOImage
A simple library that demostrates Image loading as well as supports other data types. 
Currenlty designed for image and JSON.XML files.

    To integrate into existin android project
    -> download the folder textoimage
    -> Go to project structure in Android Studio
    -> Click "+" and select import gradle project and refer to the downloaded textoimage folder
    -> compile the project and then add following lines in you app level build.gradle
    compile project(':textoimage')
    -> sync as everything should be good.
    To use the library simply call the constructor
         TextoImage textoImage = new TextoImage(context, Type.IMAGE, "url",yourImageView);
         textoImage.delegate = MyRecyclerViewAdapter.this;
         textoImage.execute();
        
        or for other formats
        
        TextoImage textoImage = new TextoImage(getApplicationContext(), Type.JSON, "url");
        textoImage.delegate = MainActivity.this;
        textoImage.execute();

    Dont forget to implement the interface AsyncResponse  for formats other then image because thats where the data will be received after loading.
    @Override
       public void dataLoaded(String output) {
       //this is the data you asked for.
     }
