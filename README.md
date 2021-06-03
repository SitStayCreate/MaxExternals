# MaxExternals
Max 7 mxj Externals
This is a package of Java classes that are to be used in the Max 7 visual programming language. What is Max 7? It's a graphical programming language for building music applications. Why use this instead of just using Java? Java's native midi functionality isn't fantastic on macOS and Windows does not let you route midi from one app to another without an external app/driver.

What if I want to use Linux?
There is another application similar to max named Puredata (PD). I have been able to use this on a Raspberry Pi 4, so it should meet whatever weird use case you can come up with. These classes probably will work with Puredata with very minor alterations (you probably need to reference a different package and change some of the settings in the pom.xml file.)
