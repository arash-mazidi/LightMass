## LightMass: Mining REST APIs for Potential Mass Assignment Vulnerabilities

## What is LightMass?
It is a tool that mines the openAPI specifications of REST APIs and identifies candidate endpoints, operations and attributes for mass assignment vulnerabilities.

## How to configure LightMass?
There is a config.json file where you can set the following parameters: 

"openAPIname" --> Name of the openAPI that LighMass should mine. 

"threshold" --> Threshold for identifying similar operations.

You also need to copy the openAPI specification in the resource folder.

## How LightMass works?
1- It reads the openAPI specification from the "resource" folder.

2- It parses each specification file.

3- It reports potential vulnerable endpoints, operations, and attributes in each API.