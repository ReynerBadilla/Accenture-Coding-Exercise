/*
Reyner Badilla Arias Coding Exercise
Total time: 6 hours 30 minutes

Useful resources to resolve the problem

Find the files: https://stackoverflow.com/questions/3953965/get-a-list-of-all-the-files-in-a-directory-recursive
Replace the same words: https://www.tutorialspoint.com/groovy/groovy_replaceall.htm
Read and write the files: https://www.tutorialspoint.com/groovy/groovy_file_io.htm
Copy and paste files: https://docs.oracle.com/javase/7/docs/api/java/nio/file/Files.html#copy%28java.nio.file.Path,%20java.nio.file.Path,%20java.nio.file.CopyOption...%29
Try-catch: https://code-maven.com/groovy-exception-handling

Technique used to solve the problem: Recursive algorithm.
*/

import groovy.io.FileType
import java.io.File
import java.nio.file.FileSystems
import java.nio.file.Path
import java.nio.file.Files
import java.text.SimpleDateFormat
import java.util.Date

class PatternReplacer {

    /*
    Main search and replace algorithm method. Here, all directories and subdirectories are recursively traversed,
    an effective and versatile technique for processing files in a hierarchical directory structure.
    */
    def searchAndReplaceAlgorithm(String directory, String patternText, String newPattern, String directoryFilesModified = null) {
        def fileList = [] // List that will contain all files with the searched pattern
        def analyzedFileCount = 0
        logging("\nStart time: ${simpleDateFormat()}") // "logging" is a method used throughout the process to record and print all actions performed by the code.

        try {
            def dir = new File(directory)
            dir.eachFileRecurse(FileType.FILES) { // Here, the dir.eachFileRecurse function performs a recursive search in the specified directory
                file ->
                analyzedFileCount++
                def text = file.text
                def modifiedText = text.replaceAll(patternText, newPattern) // Here, we search for the pattern in the file, and if there are matches, we modify it

                // If the original text is different from the processed text, it means it has been modified, so we proceed to create a backup, update the original file, etc.
                if (!text.equals(modifiedText)) {
                    backup(file)
                    file.text = modifiedText
                    fileList << file.toString()
                }
            }

            logging("\nTotal files analyzed: ${analyzedFileCount}")

            // Once the search process is finished, if the file list is empty, no matches were found. Otherwise, if matches were found
            if (fileList.isEmpty()) {
                logging("\nNo matches found for pattern '${patternText}' in the files.")
            } else {
                logging("\nComplete replacement. The following files contain pattern '${patternText}' and were modified with pattern '${newPattern}': ")
                fileList.each {
                    logging("${it} ")
                }
            }

            // We check if the file path for recording modified files has been specified.
            if (directoryFilesModified != null) {
                outputFilesModified(directoryFilesModified, fileList)
            }

        } catch (Exception e) {
            logging("\nError when parsing filesin method (searchAndReplaceAlgorithm), description: ${e}")
        }

        logging("\nEnd time: ${simpleDateFormat()}\n")
    }

    // Method to add the list of modified files to the specified file path, the list of files will be saved by date
    def outputFilesModified(String directoryFilesModified, List < String > list) {
        try {
            def dir = new File(directoryFilesModified)

            if (!list.isEmpty()) {
                def output = simpleDateFormat() + '\n' + list.collect {
                    it
                }.join('\n') + '\n\n'
                dir.append(output)
            } else {
                dir.append("${simpleDateFormat()}\nNo files were modified\n\n")
            }

        } catch (Exception e) {
            logging("\nError parsing files in method (outputFilesModified), description: ${e}")
        }
    }

    // Method to create a backup of the file, a folder will be created in the format backup_yyyyMMddHHmmss_file_name
    def backup(File file) {
        try {
            def dir = "./backups/backup_${simpleDateFormat("yyyyMMddHHmmss ").toString()}_${file.name}"
            def folder = new File(dir)
            folder.mkdirs()

            Path source = FileSystems.getDefault().getPath(file.toString())
            Path target = FileSystems.getDefault().getPath(dir, file.name)
            Files.copy(source, target);
        } catch (Exception e) {
            logging("Error creating backup for file ${file.name} in method (backup), description: ${e}")
        }
    }

    // Method to record the search process and also print information to the console
    def logging(String message) {
        try {  
            def dir = new File('./logging.txt')
            dir.append(message)
            print(message)
        }catch  (Exception e) {
            print("Error creating log for file ${file.name} in method (logging), description: ${e}")
        }
    }

    // Method to format a date with the format day:month:year hour:minutes:seconds, we can also receive the desired format
    def simpleDateFormat(String dateFormat = "dd-MMM-yyyy HH:mm:ss") {
        return new Date().format(dateFormat)
    }
}


// Flow where we will specify the pattern, new text, directory and a path to a file for outputting a list of which files were modified.
def patternText = 'Jackson'
def newText = 'Jordan'
def directory = './files'
def directoryFilesModified = './modifiedFiles.txt'

def patternReplacer = new PatternReplacer()
patternReplacer.searchAndReplaceAlgorithm(directory, patternText, newText, directoryFilesModified)
