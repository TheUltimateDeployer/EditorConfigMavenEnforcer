# Maven Enforcer rule for Editorconfig conformance checks

This Maven enforcer rule will check the conformance of the text files with the configured Editorconfig parameters.

## Configuration
In the integration-test module is an example pom.xml which shows how to integrate the checks.

## Status
Not all Editorconfig values are checked right now.

Here is a description of the parameters:

 - fileFilterRegexp: Java Regexp for the files which should be checked
 - rootDir: The top level directory which should be travesed
 - shouldFail: Should the Maven execution be stopped ("true") or should it display a warning ("false")

## Platform specific support
It is possible to specify the EOF platform specific (Windows should be CRLF, Unix and Apple should be LF) 

end_of_line = os
 
or 
 
end_of_line = system
 
