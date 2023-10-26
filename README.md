# Instructions for the Accenture Coding Exercise

Greetings! Below, you'll find a more detailed guide on how to run the code for the technical test, along with an explanation of the project's structure.

## Prerequisites

1. To run the project, you'll need Java 7 or higher and Groovy version 2 or higher.

## Project Structure

2. The following is the project's structure. You'll find a description of each part of the project below:

   ![image](https://github.com/ReynerBadilla/Accenture-Coding-Exercise/assets/122590811/f88ce50b-c8aa-4795-a9b0-5d20d4d4cc21)

   a. **backups**: This is a folder where backups of modified files will be stored. Each file will have its own folder, and the folder structure will be "backup_yyyyMMddHHmmss_file_name."

   b. **files**: This folder contains all the files and subdirectories used for the algorithm's search. You can create as many directories and folders as you need.

   c. **logging.txt**: This file keeps a record during execution, capturing information such as start time, errors, end time, patterns found, and their locations.

   d. **main.groovy**: This Groovy file contains the code for the exercise.

   e. **modifiedFiles.txt**: This file is optional, as mentioned in point 2 of the exercise document. It can be used to log modified files. However, users have the option not to use it or to specify a different location.

## Customization

3. To modify the search pattern, replacement pattern, and directory, simply edit the following lines in the `main.groovy` file:

   ![image](https://github.com/ReynerBadilla/Accenture-Coding-Exercise/assets/122590811/fc3bccbc-e532-481a-b220-a126ded0a967)

5. Finally, to run the project, use the command:

```shell
groovy .\main.groovy
```

### Created by Reyner Badilla Arias
