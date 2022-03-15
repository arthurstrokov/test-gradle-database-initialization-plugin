package com.gmail.arthurstrokov.plugin.util

class InputService {

    static def readFromFile(String fileName) {
        List<String> list = new ArrayList<>();
        String sql = "";
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                sql = myReader.nextLine();
                list.add(sql);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(("An error occurred."));
            e.printStackTrace();
        }
        return list;
    }
}
