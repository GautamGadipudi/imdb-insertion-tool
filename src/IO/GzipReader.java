package IO;

import DB.Insertion;
import Entities.*;
import Entities.Writer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

public final class GzipReader {
    public static void processTitleFile(String fileName) {
        List<Title> titles = new ArrayList<>();
        try {
            InputStream fileStream = new FileInputStream(fileName);
            InputStream gzipStream = new GZIPInputStream(fileStream);
            Reader decoder = new InputStreamReader(gzipStream, "UTF-8");
            BufferedReader buffered = new BufferedReader(decoder);

            String a = buffered.readLine();
            String[] headers = a.split("\t");
            while ((a = buffered.readLine()) != null) {
                String[] values = a.split("\t");
                Title t = new Title();
                for (int i = 0; i < values.length; i++) {
                    if (headers[i].equals("tconst")) {
                        String integerId = values[i].substring(2);
                        t.setId(Integer.parseInt(integerId));
                    } else if (headers[i].equals("titleType")) {
                        t.setType(values[i]);
                    } else if (headers[i].equals("primaryTitle")) {
                        t.setPrimaryTitleName(values[i]);
                    } else if (headers[i].equals("originalTitle")) {
                        t.setOriginalTitleName(values[i]);
                    } else if (headers[i].equals("startYear")) {
                        if (values[i].equals("\\N"))
                            values[i] = "0";
                        t.setStartYear(Integer.parseInt(values[i]));
                    } else if (headers[i].equals("endYear")) {
                        if (values[i].equals("\\N"))
                            values[i] = "0";
                        t.setEndYear(Integer.parseInt(values[i]));
                    } else if (headers[i].equals("runtimeMinutes")) {
                        if (values[i].equals("\\N"))
                            values[i] = "0";
                        t.setRuntimeMinutes(Integer.parseInt(values[i]));
                    } else if (headers[i].equals("genres")) {
                        t.setGenres(values[i]);
                    }
                }
                titles.add(t);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        DB.Insertion.insertTitles(titles);
    }

    public static void processNameFile(String fileName) {
        List<Name> names = new ArrayList<>();
        try {
            InputStream fileStream = new FileInputStream(fileName);
            InputStream gzipStream = new GZIPInputStream(fileStream);
            Reader decoder = new InputStreamReader(gzipStream, "UTF-8");
            BufferedReader buffered = new BufferedReader(decoder);

            String a = buffered.readLine();
            String[] headers = a.split("\t");
            while ((a = buffered.readLine()) != null) {
                String[] values = a.split("\t");
                Name t = new Name();
                for (int i = 0; i < values.length; i++) {
                    if (headers[i].equals("nconst")) {
                        String integerId = values[i].substring(2);
                        t.setId(Integer.parseInt(integerId));
                    } else if (headers[i].equals("primaryName")) {
                        t.setPrimaryName(values[i]);
                    } else if (headers[i].equals("birthYear")) {
                        if (values[i].equals("\\N"))
                            values[i] = "0";
                        t.setBirthYear(Integer.parseInt(values[i]));
                    } else if (headers[i].equals("deathYear")) {
                        if (values[i].equals("\\N"))
                            values[i] = "0";
                        t.setDeathYear(Integer.parseInt(values[i]));
                    }
                }
                names.add(t);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        DB.Insertion.insertNames(names);
    }

    public static void processCrewFile(String fileName) {
        List<Director> directors = new ArrayList<>();
        List<Entities.Writer> writers = new ArrayList<>();
        try {
            InputStream fileStream = new FileInputStream(fileName);
            InputStream gzipStream = new GZIPInputStream(fileStream);
            Reader decoder = new InputStreamReader(gzipStream, "UTF-8");
            BufferedReader buffered = new BufferedReader(decoder);

            String a = buffered.readLine();
            String[] headers = a.split("\t");
            while ((a = buffered.readLine()) != null) {
                String[] values = a.split("\t");

                int titleId = Integer.parseInt(values[0].substring(2));
                if (!values[1].isBlank() && !values[1].isEmpty() && !values[1].equals("\\N")) {
                    String[] nconsts = values[1].split(",");
                    for (var nconst : nconsts) {
                        Director d = new Director();
                        d.setTitleId(titleId);
                        d.setNameId(Integer.parseInt(nconst.substring(2)));
                        directors.add(d);
                    }
                }
                if (!values[2].isBlank() && !values[2].isEmpty() && !values[2].equals("\\N")) {
                    String[] nconsts = values[1].split(",");
                    for (var nconst : nconsts) {
                        Entities.Writer d = new Writer();
                        d.setTitleId(titleId);
                        d.setNameId(Integer.parseInt(nconst.substring(2)));
                        writers.add(d);
                    }
                }
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        Insertion.insertDirectors(directors);
        Insertion.insertWriters(writers);
    }

    public static void processPrincipalFile(String fileName) {
        List<Principal> names = new ArrayList<>();
        try {
            InputStream fileStream = new FileInputStream(fileName);
            InputStream gzipStream = new GZIPInputStream(fileStream);
            Reader decoder = new InputStreamReader(gzipStream, "UTF-8");
            BufferedReader buffered = new BufferedReader(decoder);

            String a = buffered.readLine();
            String[] headers = a.split("\t");
            while ((a = buffered.readLine()) != null) {
                String[] values = a.split("\t");
                Principal t = new Principal();
                for (int i = 0; i < values.length; i++) {
                    if (headers[i].equals("tconst")) {
                        String integerId = values[i].substring(2);
                        t.setTitleId(Integer.parseInt(integerId));
                    } else if (headers[i].equals("ordering")) {
                        t.setOrderNumber(Integer.parseInt(values[i]));
                    } else if (headers[i].equals("nconst")) {
                        String integerId = values[i].substring(2);
                        t.setNameId(Integer.parseInt(integerId));
                    } else if (headers[i].equals("category")) {
                        t.setCategory(values[i]);
                    } else if (headers[i].equals("job")) {
                        t.setJob(values[i]);
                    } else if (headers[i].equals("characters")) {
                        t.setCharacters(values[i]);
                    }
                }
                names.add(t);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        DB.Insertion.insertPrincipals(names);
    }

    public static void processRatingFile(String fileName) {
        List<Rating> names = new ArrayList<>();
        try {
            InputStream fileStream = new FileInputStream(fileName);
            InputStream gzipStream = new GZIPInputStream(fileStream);
            Reader decoder = new InputStreamReader(gzipStream, "UTF-8");
            BufferedReader buffered = new BufferedReader(decoder);

            String a = buffered.readLine();
            String[] headers = a.split("\t");
            while ((a = buffered.readLine()) != null) {
                String[] values = a.split("\t");
                Rating t = new Rating();
                for (int i = 0; i < values.length; i++) {
                    if (headers[i].equals("tconst")) {
                        String integerId = values[i].substring(2);
                        t.setTitleId(Integer.parseInt(integerId));
                    } else if (headers[i].equals("averageRating")) {
                        if (values[i].equals("\\N"))
                            values[i] = "0";
                        t.setAverageRating(Float.parseFloat(values[i]));
                    } else if (headers[i].equals("numVotes")) {
                        if (values[i].equals("\\N"))
                            values[i] = "0";
                        t.setVoteCount(Integer.parseInt(values[i]));
                    }
                }
                names.add(t);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        DB.Insertion.insertRatings(names);
    }

    public static void processEpisodeFile(String fileName) {
        List<Episode> names = new ArrayList<>();
        try {
            InputStream fileStream = new FileInputStream(fileName);
            InputStream gzipStream = new GZIPInputStream(fileStream);
            Reader decoder = new InputStreamReader(gzipStream, "UTF-8");
            BufferedReader buffered = new BufferedReader(decoder);

            String a = buffered.readLine();
            String[] headers = a.split("\t");
            while ((a = buffered.readLine()) != null) {
                String[] values = a.split("\t");
                Episode t = new Episode();
                for (int i = 0; i < values.length; i++) {
                    if (headers[i].equals("tconst")) {
                        String integerId = values[i].substring(2);
                        t.setId(Integer.parseInt(integerId));
                    } else if (headers[i].equals("parentTconst")) {
                        String integerId = values[i].substring(2);
                        t.setTitleId(Integer.parseInt(integerId));
                    } else if (headers[i].equals("seasonNumber")) {
                        if (values[i].equals("\\N"))
                            values[i] = "0";
                        t.setSeasonNumber(Integer.parseInt(values[i]));
                    } else if (headers[i].equals("episodeNumber")) {
                        if (values[i].equals("\\N"))
                            values[i] = "0";
                        t.setEpisodeNumber(Integer.parseInt(values[i]));
                    }
                }
                names.add(t);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        DB.Insertion.insertEpisodes(names);
    }

    public static void processAkaFile(String fileName) {
        List<Aka> names = new ArrayList<>();
        try {
            InputStream fileStream = new FileInputStream(fileName);
            InputStream gzipStream = new GZIPInputStream(fileStream);
            Reader decoder = new InputStreamReader(gzipStream, "UTF-8");
            BufferedReader buffered = new BufferedReader(decoder);

            String a = buffered.readLine();
            String[] headers = a.split("\t");
            while ((a = buffered.readLine()) != null) {
                String[] values = a.split("\t");
                Aka t = new Aka();
                for (int i = 0; i < values.length; i++) {
                    if (headers[i].equals("titleId")) {
                        String integerId = values[i].substring(2);
                        t.setTitleId(Integer.parseInt(integerId));
                    } else if (headers[i].equals("ordering")) {
                        t.setOrderNumber(Integer.parseInt(values[i]));
                    } else if (headers[i].equals("title")) {
                        t.setTitleName(values[i]);
                    } else if (headers[i].equals("region")) {
                        t.setRegion(values[i]);
                    } else if (headers[i].equals("language")) {
                        t.setLanguage(values[i]);
                    } else if (headers[i].equals("types")) {
                        t.setTypes(values[i]);
                    } else if (headers[i].equals("attributes")) {
                        t.setAttributes(values[i]);
                    } else if (headers[i].equals("isOriginalTitle")) {
                        boolean isOriginal = (values[i].equals("1") ? true : false);
                        t.setOriginalTitle(isOriginal);
                    }
                }
                names.add(t);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        DB.Insertion.insertAkas(names);
    }

}
