import DB.Insertion;
import Entities.Director;
import Entities.Name;
import Entities.Title;
import Entities.Writer;
import IO.GzipReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

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
        GzipReader.processNameFile(nameFile);
        System.out.println("Dumped names into db!");
        GzipReader.processAkaFile(akaFile);
        System.out.println("Dumped akas into db!");
        GzipReader.processCrewFile(crewFile);
        System.out.println("Dumped crew into db!");
        GzipReader.processEpisodeFile(episodeFile);
        System.out.println("Dumped episodes into db!");
        GzipReader.processPrincipalFile(principalFile);
        System.out.println("Dumped principals into db!");
        GzipReader.processRatingFile(ratingFile);
        System.out.println("Dumped ratings into db!");
    }
}
