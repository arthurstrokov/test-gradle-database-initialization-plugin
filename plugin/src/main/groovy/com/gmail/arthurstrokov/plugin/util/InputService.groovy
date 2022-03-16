package com.gmail.arthurstrokov.plugin.util

class InputService {

    static List<String> readFromFile(String fileName) {
        List<String> list = new ArrayList<>()
        try {
            File myObj = new File(fileName)
            Scanner myReader = new Scanner(myObj)
            while (myReader.hasNextLine()) {
                String sql = myReader.nextLine()
                list.add(sql)
            }
            myReader.close()
        } catch (FileNotFoundException e) {
            System.out.println(("An error occurred."))
            e.printStackTrace()
        }
        return list
    }
}
