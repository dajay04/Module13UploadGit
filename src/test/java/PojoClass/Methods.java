package PojoClass;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Methods
{
    public JsonObject createJSONOBJ(String arg1)
    {
        JsonObject jsonObject=  new JsonParser().parse(arg1).getAsJsonObject();
        return jsonObject;
    }

    public String readJsonAsString(String path) throws Exception
    {
        JsonParser parser = new JsonParser();
        File file = new File(path);
        FileReader fileReader= new FileReader(file);
        Object obj = parser.parse(fileReader);
        return obj.toString();
    }
}
