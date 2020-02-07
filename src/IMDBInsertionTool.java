/**
 * IMDBInsertionTool.java
 *
 * @author Gautam Gadipudi
 *
 * @id gg7148
 *
 * @description This is the question 4 of assignment 1. The fileNames can be editted to
 * provide correct paths.*/
import IO.GzipReader;

public class IMDBInsertionTool {

    public static void main(String[] args) {
        String titleFile = "title.basics.tsv.gz";
        String nameFile = "name.basics.tsv.gz";
        String akaFile = "title.akas.tsv.gz";
        String crewFile = "title.crew.tsv.gz";
        String episodeFile = "title.episode.tsv.gz";
        String principalFile = "title.principals.tsv.gz";
        String ratingFile = "title.ratings.tsv.gz";

        GzipReader.processTitleFile(titleFile);
        System.out.println("Dumped titles into db!");
        GzipReader.processRatingFile(ratingFile);
        System.out.println("Dumped ratings into db!");
        GzipReader.processEpisodeFile(episodeFile);
        System.out.println("Dumped episodes into db!");
        GzipReader.processAkaFile(akaFile);
        System.out.println("Dumped akas into db!");
        GzipReader.processNameFile(nameFile);
        System.out.println("Dumped names into db!");
        GzipReader.processCrewFile(crewFile);
        System.out.println("Dumped crew into db!");
        GzipReader.processPrincipalFile(principalFile);
        System.out.println("Dumped principals into db!");

    }
} //IMDBInsertionTool
