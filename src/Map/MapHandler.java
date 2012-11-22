/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import beliar.MeshContainer;
import beliar.ValuesTerrain;
import com.google.gson.JsonArray;
import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import java.util.HashMap;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jme3.material.Material;
import java.io.FileReader;
import java.util.Random;

/**
 *
 * @author Martin
 */
public class MapHandler {

    private JsonParser parser = new JsonParser();
    private String name = "Cell";
    private float degree = 45.55f;
    private AssetManager assetManager;
    private com.jme3.scene.Node mapNode;
    private MeshContainer containerMapHandler;
    private int sizeX;
    private int sizeY;
    private int terrainMap[][];
    private boolean positionBuildings[][];
    private int positionSoulAbyss[][] = new int[20][20];
    private int positionEnter[][];
    private int positionArtefacts[][];
    private Vector3f positionHellCenter = new Vector3f(Vector3f.ZERO);
    private Spatial output = null;
    private HashMap<Integer[], Spatial> mapMeshes = new HashMap<Integer[], Spatial>();

    public MapHandler(MeshContainer containerMapHandler, com.jme3.scene.Node mapNode, AssetManager assetManager) {
        this.mapNode = mapNode;
        this.assetManager = assetManager;
        this.containerMapHandler = containerMapHandler;

        readMapFile("level01");
        initMap();
        wirteMap();
        writeMapNodes();
    }

    private void readMapFile(String levelName) {
        try {
            Object file = parser.parse(new FileReader("assets/Maps/" + levelName + ".json"));
            JsonObject jsonObject = (JsonObject) file;

            String name = jsonObject.get("name").toString();
            System.out.println("Name:" + name);

            JsonArray terrainJSONArray = jsonObject.get("terrain").getAsJsonArray();

            sizeX = terrainJSONArray.size();
            sizeY = terrainJSONArray.get(0).getAsJsonArray().size();

            terrainMap = new int[sizeX][sizeY];
            positionBuildings = new boolean[sizeX][sizeY];

            //Jedes Element des Terrains abfragen
            for (int i = 0; i < terrainJSONArray.size(); i++) {
                //Konkrete Zalen aus den Arrays auslesen
                for (int j = 0; j < terrainJSONArray.get(i).getAsJsonArray().size(); j++) {
                    System.out.println("I:" + i);
                    System.out.println("J:" + j);
                    terrainMap[i][j] = terrainJSONArray.get(i).getAsJsonArray().get(j).getAsInt();
                    System.out.println(terrainMap[i][j]);
                }

            }

            //Hellcenter
            JsonArray hellcenter = jsonObject.get("hellceter").getAsJsonArray();
            positionHellCenter.x = hellcenter.get(0).getAsInt();
            positionHellCenter.z = hellcenter.get(1).getAsInt();

            //SoulGrounds
            JsonArray positionSouls = jsonObject.get("soulAbyss").getAsJsonArray();

            for (int i = 0; i < positionSouls.size(); i++) {
                for (int j = 0; j < positionSouls.get(i).getAsJsonArray().size(); j++) {
                    //System.out.println("I:" + i);
                    //System.out.println("J:" + j);
                    positionSoulAbyss[i][j] = positionSouls.get(i).getAsJsonArray().get(j).getAsInt();
                }
            }


        } catch (Exception e) {
            //System.out.println("Error here,BITCH!");
            //System.out.println(e.toString());
        }
    }

    private void createMapCube(int type, int x, int y) {
        Vector3f position = new Vector3f(0, 0, 0);
        Vector3f transformation = new Vector3f(x, 0, y);

        Box b = new Box(position, 0.5f, 0.5f, 0.5f);
        Geometry geom = new Geometry("MapElement", b);
        geom.move(transformation);
        Material geoMat = assetManager.loadMaterial("Materials/_standardTerrain.j3m");
        switch (type) {
            case 0:
                 
                
                int random = (int) (Math.random()*5+1);
                switch(random)
                {
                    case 1: geoMat.setTexture("DiffuseMap", assetManager.loadTexture("Textures/terrain/redRock.jpg"));
                        break;
                    case 2:geoMat.setTexture("DiffuseMap", assetManager.loadTexture("Textures/terrain/redRock_2.jpg"));
                        break;
                    case 3:geoMat.setTexture("DiffuseMap", assetManager.loadTexture("Textures/terrain/redRock_3.jpg"));
                        break;
                    case 4:geoMat.setTexture("DiffuseMap", assetManager.loadTexture("Textures/terrain/redRock_4.jpg"));
                        break;
                   // case 5:geoMat.setTexture("DiffuseMap", assetManager.loadTexture("Textures/terrain/redRock_5.jpg"));
                   //     break;
                    default:
                        geoMat.setTexture("DiffuseMap", assetManager.loadTexture("Textures/terrain/redRock.jpg"));
                        break;
                }
                
                geom.setMaterial(geoMat);
                break;
            case -1:
                geoMat.setTexture("DiffuseMap", assetManager.loadTexture("Textures/terrain/redRock_5.jpg"));
                break;

            default:
                return;
        }

        mapNode.attachChild(geom);

    }

    private void initMap() {
        System.out.println("SIZE" + sizeX);
        setHellCenterAt((int) positionHellCenter.x, (int) positionHellCenter.z);
        
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                if (terrainMap[x][y] <= ValuesTerrain.EARTH) {
                    int mapType = terrainMap[x][y];
                    createMapCube(mapType, x, y);
                    try {
                        handleBuilding(new Vector3f(x, 0, y), mapType);
                    } catch (Exception e) {
                        System.out.println("ERROR");
                    }
                    positionBuildings[x][y] = true;
                } else {
                    positionBuildings[x][y] = false;
                }
            }
        }
    }

    private void setHellCenterAt(int x, int y) {

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                terrainMap[x + i][y + j] = ValuesTerrain.HELLCENTER;
            }
        }

        Spatial center = containerMapHandler.hellCenter.clone();
        Vector3f position = new Vector3f(x, 0, y);
        center.move(position);
        mapNode.attachChild(center);
    }
    
    private void setSoulGroundAt(int x,int y) {
            
        for (int i=0;i<=1;i++)
        {
            for (int j=0;j<=1;j++)
            {
                terrainMap[x+i][y+j]= ValuesTerrain.SOULABYSS;
            }
        }
    }

    public void handleBuilding(Vector3f position, int type) {
        System.out.println("MapHandler: handleBuilding()");

        if (terrainMap[(int) position.x][(int) position.z] == 0) {
            //System.out.println("Bauen gestattet!");

            //Schritt1: Setze die aktuelle Position auf den uebergebenen Typen
            setCellType((int) position.x, (int) position.z, type);

            //Schritt2: Überprüfe direkte Nachbarn
            int posX = ((int) position.x);
            int posY = ((int) position.z);
            //System.out.println("PosX" + posX + "\n Position.x" + position.x);

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    checkNeighboursAt(posX + i, posY + j);
                    if (output != null) {
                        mapNode.attachChild(output);
                    }
                }
            }
        } else {
            //System.out.println("Bereits Gebaut!");
        }
    }

    private HashMap<String, Spatial> setMeshBox(int type) {
        switch (type) {
            case ValuesTerrain.HALL:
                return containerMapHandler.limbo;

            case ValuesTerrain.CAVEOFBEAST:
                return containerMapHandler.caveOfTheBeast;

            case ValuesTerrain.HALLOFANARCHY:
                return containerMapHandler.adamHall;
            case ValuesTerrain.TOMBOFMEMORY:
                return containerMapHandler.tombOfMemory;
        }
        return containerMapHandler.limbo;
    }

    private void checkNeighboursAt(int x, int y) {
        //System.out.println("CheckNeighBours At " + x + "Y:" + y);
        int type = terrainMap[x][y];
        HashMap<String, Spatial> meshBox = setMeshBox(type);

        //System.out.println("Meshbox" + meshBox.toString());

        //Überprüfe nur, an der Stelle, wo Keine Erde und nicht das Höllenzentrum stehen
        if (type != 0 && type != ValuesTerrain.HELLCENTER) {
            //System.out.println("STARTE TEST");
            mapNode.detachChildNamed(name + "" + x + "" + y);
            try {
                //GERADEN
                if ((terrainMap[x + 1][y] == type) && (terrainMap[x - 1][y] == type) && (terrainMap[x][y + 1] == ValuesTerrain.EARTH) && (terrainMap[x][y - 1] == ValuesTerrain.EARTH)) {

                    //System.out.println("X-Gerade");
                    output = meshBox.get("straigt").clone();
                    output.setName(name + "" + x + "" + y);

                } else if ((terrainMap[x + 1][y] == ValuesTerrain.EARTH) && (terrainMap[x - 1][y] == ValuesTerrain.EARTH) && (terrainMap[x][y + 1] == type) && (terrainMap[x][y - 1] == type)) {
                    //System.out.println("Y-Gerade");
                    output = meshBox.get("straigt").clone();
                    output.rotate(0, degree, 0);
                    output.setName(name + "" + x + "" + y);
                } //SEITEN
                else if ((terrainMap[x - 1][y - 1] != ValuesTerrain.EARTH)
                        && (terrainMap[x - 1][y] != ValuesTerrain.EARTH)
                        && (terrainMap[x - 1][y + 1] == ValuesTerrain.EARTH)
                        && (terrainMap[x][y - 1] != ValuesTerrain.EARTH)
                        && (terrainMap[x][y] != ValuesTerrain.EARTH)
                        && (terrainMap[x][y + 1] == ValuesTerrain.EARTH)
                        && (terrainMap[x + 1][y - 1] != ValuesTerrain.EARTH)
                        && (terrainMap[x + 1][y] != ValuesTerrain.EARTH)
                        && (terrainMap[x + 1][y + 1] == ValuesTerrain.EARTH)) {
                    //System.out.println("SEITE");
                    output = meshBox.get("side").clone();
                    //output.rotate(0, degree, 0);
                    output.setName(name + "" + x + "" + y);

                }
                
                else if ((terrainMap[x - 1][y - 1] != ValuesTerrain.EARTH)
                        && (terrainMap[x - 1][y] != ValuesTerrain.EARTH)
                        && ((terrainMap[x - 1][y + 1] == ValuesTerrain.EARTH)||(terrainMap[x][y + 1] != type))
                        && (terrainMap[x][y - 1] != ValuesTerrain.EARTH)
                        && (terrainMap[x][y] != ValuesTerrain.EARTH)
                        && ((terrainMap[x][y + 1] == ValuesTerrain.EARTH)||(terrainMap[x][y + 1] != type))
                        && (terrainMap[x + 1][y - 1] != ValuesTerrain.EARTH)
                        && (terrainMap[x + 1][y] != ValuesTerrain.EARTH)
                        && ((terrainMap[x + 1][y + 1] == ValuesTerrain.EARTH)||(terrainMap[x][y + 1] != type))) {
                    //System.out.println("SEITE");
                    output = meshBox.get("side").clone();
                    //output.rotate(0, degree, 0);
                    output.setName(name + "" + x + "" + y);

                }
                else if ((terrainMap[x - 1][y - 1] == ValuesTerrain.EARTH)
                        && (terrainMap[x - 1][y] != ValuesTerrain.EARTH)
                        && ((terrainMap[x - 1][y + 1] != ValuesTerrain.EARTH)||(terrainMap[x - 1][y + 1] != type))
                        && (terrainMap[x][y - 1] == ValuesTerrain.EARTH)
                        && (terrainMap[x][y] != ValuesTerrain.EARTH)
                        && ((terrainMap[x][y + 1] != ValuesTerrain.EARTH)||(terrainMap[x][y + 1] != type))
                        && (terrainMap[x + 1][y - 1] == ValuesTerrain.EARTH)
                        && (terrainMap[x + 1][y] != ValuesTerrain.EARTH)
                        && ((terrainMap[x + 1][y + 1] != ValuesTerrain.EARTH)||(terrainMap[x+1][y + 1] != type))) {
                    //System.out.println("SEITE");
                    output = meshBox.get("side").clone();
                    output.rotate(0, degree * 2, 0);
                    output.setName(name + "" + x + "" + y);
                } 
                else if ((terrainMap[x - 1][y - 1] == ValuesTerrain.EARTH)
                        && (terrainMap[x - 1][y] == ValuesTerrain.EARTH)
                        && (terrainMap[x - 1][y + 1] == ValuesTerrain.EARTH)
                        && ((terrainMap[x][y - 1] != ValuesTerrain.EARTH)||(terrainMap[x][y - 1] != type))
                        && (terrainMap[x][y] != ValuesTerrain.EARTH)
                        && ((terrainMap[x][y + 1] != ValuesTerrain.EARTH)||(terrainMap[x][y + 1] != type))
                        && (terrainMap[x + 1][y - 1] != ValuesTerrain.EARTH)
                        && ((terrainMap[x + 1][y] != ValuesTerrain.EARTH)||(terrainMap[x + 1][y] != type))
                        && (terrainMap[x + 1][y + 1] != ValuesTerrain.EARTH)) {
                    //System.out.println("SEITE");
                    output = meshBox.get("side").clone();
                    output.rotate(0, degree * 3, 0);
                    output.setName(name + "" + x + "" + y);
                } 
                else if ((terrainMap[x - 1][y - 1] != ValuesTerrain.EARTH)
                        && (terrainMap[x - 1][y] != ValuesTerrain.EARTH)
                        && (terrainMap[x - 1][y + 1] != ValuesTerrain.EARTH)
                        && (terrainMap[x][y - 1] != ValuesTerrain.EARTH)
                        && (terrainMap[x][y] != ValuesTerrain.EARTH)
                        && (terrainMap[x][y + 1] != ValuesTerrain.EARTH)
                        && (terrainMap[x + 1][y - 1] == ValuesTerrain.EARTH)
                        && (terrainMap[x + 1][y] == ValuesTerrain.EARTH)
                        && (terrainMap[x + 1][y + 1] == ValuesTerrain.EARTH)) {
                    //System.out.println("SEITE");
                    output = meshBox.get("side").clone();
                    output.rotate(0, degree, 0);
                    output.setName(name + "" + x + "" + y);
                } //KURVEN
                else if ((terrainMap[x][y - 1] == type) && (terrainMap[x + 1][y] == type) && (terrainMap[x - 1][y] != type) && (terrainMap[x][y + 1] != type)) {
                    //System.out.println("KURVE:-Y/+X");
                    output = meshBox.get("curve").clone();
                    output.setName(name + "" + x + "" + y);
                } else if ((terrainMap[x][y - 1] == type) && (terrainMap[x + 1][y] != type) && (terrainMap[x - 1][y] == type) && (terrainMap[x][y + 1] != type)) {
                    //System.out.println("KURVE:+Y/-X/+90");
                    output = meshBox.get("curve").clone();
                    output.rotate(0, degree, 0);
                    output.setName(name + "" + x + "" + y);
                } else if ((terrainMap[x][y - 1] != type) && (terrainMap[x + 1][y] != type) && (terrainMap[x - 1][y] == type) && (terrainMap[x][y + 1] == type)) {
                    //System.out.println("KURVE:Y/-X+180");
                    output = meshBox.get("curve").clone();
                    output.rotate(0, degree * 2, 0);
                    output.setName(name + "" + x + "" + y);
                } else if ((terrainMap[x][y - 1] != type) && (terrainMap[x + 1][y] == type) && (terrainMap[x - 1][y] != type) && (terrainMap[x][y + 1] == type)) {
                    //System.out.println("KURVE:+Y/+X+270");
                    output = meshBox.get("curve").clone();
                    output.rotate(0, degree * 3, 0);
                    output.setName(name + "" + x + "" + y);
                } //3KREUZUNG
                else if ((terrainMap[x - 1][y] == type)
                        && (terrainMap[x][y - 1] == type)
                        && (terrainMap[x + 1][y] == type)
                        && (terrainMap[x][y + 1] != type)
                        && (terrainMap[x][y + 1] == ValuesTerrain.EARTH)) {

                    //System.out.println("Kreuzung 0");
                    output = meshBox.get("3cross").clone();
                    output.rotate(0, 0, 0);
                    output.setName(name + "" + x + "" + y);
                } else if ((terrainMap[x - 1][y] == type)
                        && (terrainMap[x][y - 1] != type)
                        && (terrainMap[x + 1][y] == type)
                        && (terrainMap[x][y + 1] == type)
                        && (terrainMap[x][y - 1] == ValuesTerrain.EARTH)) {

                    //System.out.println("Kreuzung 180");
                    output = meshBox.get("3cross").clone();
                    output.rotate(0, degree * 2, 0);
                    output.setName(name + "" + x + "" + y);
                } else if ((terrainMap[x - 1][y] == type)
                        && (terrainMap[x][y + 1] == type)
                        && (terrainMap[x + 1][y] != type)
                        && (terrainMap[x][y + 1] == type)
                        && (terrainMap[x + 1][y] == ValuesTerrain.EARTH)) {

                    //System.out.println("Kreuzung 270");
                    output = meshBox.get("3cross").clone();
                    output.rotate(0, degree * 1, 0);
                    output.setName(name + "" + x + "" + y);

                } else if ((terrainMap[x - 1][y] != type)
                        && (terrainMap[x][y + 1] == type)
                        && (terrainMap[x + 1][y] == type)
                        && (terrainMap[x][y + 1] == type)
                        && (terrainMap[x - 1][y] == ValuesTerrain.EARTH)) {
                    //System.out.println("Kreuzung 90");
                    output = meshBox.get("3cross").clone();
                    output.rotate(0, degree * 3, 0);
                    output.setName(name + "" + x + "" + y);

                } //4KREUZUNG
                else if ((terrainMap[x - 1][y] == type) && (terrainMap[x][y - 1] == type) && (terrainMap[x + 1][y] == type) && (terrainMap[x][y + 1] == type)) {

                    //System.out.println("4Kreuzung");
                    output = meshBox.get("4cross").clone();
                    output.setName(name + "" + x + "" + y);

                } //Ende
                else if (
                        (terrainMap[x - 1][y] == type) && (terrainMap[x][y - 1] != type) && (terrainMap[x + 1][y] != type) && (terrainMap[x][y + 1] != type)) {
                    //System.out.println("Ende 0");
                    output = meshBox.get("end").clone();
                    output.rotate(0, degree, 0);
                    output.setName(name + "" + x + "" + y);
                } else if ((terrainMap[x - 1][y] != type) && (terrainMap[x][y - 1] == type) && (terrainMap[x + 1][y] != type) && (terrainMap[x][y + 1] != type)) {
                    //System.out.println("Ende 90");
                    output = meshBox.get("end").clone();
                    output.setName(name + "" + x + "" + y);

                } else if ((terrainMap[x - 1][y] != type) && (terrainMap[x][y - 1] != type) && (terrainMap[x + 1][y] == type) && (terrainMap[x][y + 1] != type)) {
                    //System.out.println("Ende 180");
                    output = meshBox.get("end").clone();
                    output.rotate(0, degree * 3, 0);
                    output.setName(name + "" + x + "" + y);
                } else if ((terrainMap[x - 1][y] != type) && (terrainMap[x][y - 1] != type) && (terrainMap[x + 1][y] != type) && (terrainMap[x][y + 1] == type)) {
                    //System.out.println("Ende 270");
                    output = meshBox.get("end").clone();
                    output.rotate(0, degree * 2, 0);
                    output.setName(name + "" + x + "" + y);
                } else if (terrainMap[x][y - 1] == 2 && terrainMap[x + 1][y] == type && terrainMap[x][y + 1] == type && terrainMap[x - 1][y] == type) {
                    //System.out.println("SIDE");
                    output = meshBox.get("side").clone();
                    //output.rotate(0,degree*3,0);
                    output.setName(name + "" + x + "" + y);
                } else {
                    //System.out.println("No!");
                    output = meshBox.get("4cross").clone();
                    output.rotate(0, 0, 0);
                    output.setName(name + "" + x + "" + y);

                }


            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("MapHandler: checkNeighbour() catch");
                output = meshBox.get("4cross").clone();
                output.setName(name + "" + x + "" + y);
            }
            output.move(x, 0, y);
            //System.out.println("Outline" + output.getTriangleCount());
        }
    }

    public void placeBuilding(int x, int y) {
        positionBuildings[x][y] = false;
    }

    public boolean getPlacesBuildingAt(int x, int y) {
        return positionBuildings[x][y];
    }

    private void setCellType(int x, int z, int type) {
        System.out.println("MapHandler: setCellType() type: " + type);
        terrainMap[x][z] = type;
        if(type == ValuesTerrain.HALL || type == ValuesTerrain.HALLOFANARCHY || type == ValuesTerrain.TOMBOFMEMORY ||
                type == ValuesTerrain.CAVEOFBEAST){
            MapController.setHallwayAt(x, z);
        }
    }

    public int getCellType(int x, int y) {
        return terrainMap[x][y];
    }
    
    private void wirteMap(){
        System.out.println("MapHandler: writeMap()");
        MapController.setMap(terrainMap);
    }
    
    private void writeMapNodes(){
        System.out.println("MapHandler: writeMapNodes()");
        MapController.setMapNodes();
    }
}
