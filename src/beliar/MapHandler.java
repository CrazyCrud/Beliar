/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beliar;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import java.io.File;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

/**
 *
 * @author Martin
 */
public class MapHandler {
    
    private String name ="Cell";
    private float degree = 45.55f;
    private AssetManager assetManager;
    private com.jme3.scene.Node mapNode;
    private MeshContainer containerMapHandler;
    private int terrainMap[][];
    
    private int positionSoulAbyss[][];
    private int positionEnter[][];
    private int positionArtefacts[][];
    
    
    private Spatial output = null;
    
    private HashMap<Integer[],Spatial> mapMeshes = new HashMap<Integer[], Spatial>();

    MapHandler(MeshContainer containerMapHandler,com.jme3.scene.Node mapNode,AssetManager assetManager) {       
        this.mapNode = mapNode;
        this.assetManager = assetManager;
        this.containerMapHandler=containerMapHandler;
        
        readMapFile("level01");
        initMap();
        
        
    }
    
    private void readMapFile(String levelName)
    {
        try
        {
        File fXmlFile = new File("assets/Maps/level01.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dbuilder = dbFactory.newDocumentBuilder();
        Document doc;
        
        doc  = dbuilder.parse(fXmlFile);
        //doc.getDocumentElement().normalize();
  
        //NodeList nList = doc.getChildNodes();

        
        }
         catch (Exception e) {
             System.out.println("!!!ERROR!!!");
		e.printStackTrace();
	  }
    }

    private void createMapCube(int type, int x, int y)
    {
        System.out.println("createMapCube");
        Vector3f position = new Vector3f(0, 0, 0);
        Vector3f transformation = new Vector3f(x, 0, y);

        Box b = new Box(position, 0.5f,0.5f,0.5f);
        Geometry geom = new Geometry("MapElement", b);
       geom.move(transformation);
        switch (type)
        {
            case 0: geom.setMaterial(assetManager.loadMaterial("Materials/_standardTerrain.j3m"));break;
            case 1: geom.setMaterial(assetManager.loadMaterial("Materials/_standardTerrain.j3m"));break;
                
            default:
                return;
        }
        
       mapNode.attachChild(geom);
        
    }
    
    
    private void initMap()
    {
        //DEBUG!!!
       terrainMap= new int [128][128];
        setHellCenterAt(10, 10);
        for (int x=0;x<127;x++)
        {
            for(int y=0;y<63;y++)
            {
                if(terrainMap[x][y]!=ValuesTerrain.HELLCENTER)
                {
                terrainMap[x][y]=0;
                int mapType = terrainMap[x][y];
		createMapCube(mapType, x, y);
                }
            }
        }
        
        

    }
    
    private void setHellCenterAt(int x,int y)
    {
        
        for(int i=-1;i<=1;i++)
        {
            for(int j=-1;j<=1;j++)
            {
                terrainMap[x+i][y+j]= ValuesTerrain.HELLCENTER;
            }
        }

        
        Spatial center = containerMapHandler.hellCenter.clone();
        Vector3f position = new Vector3f(x, 0, y);
        center.move(position);
        mapNode.attachChild(center);
    }
    private void setCellType(int x, int y, int type)
    {
        terrainMap[x][y]= type;
    }
    
    
    public void handleBuilding(Vector3f position,int type)
    {
        System.out.println("HandleBuilding");

        
                
       if(terrainMap[(int)position.x][(int)position.z] == 0)
       {
           System.out.println("Bauen gestattet!");
           
           //Schritt1: Setze die aktuelle Position auf den uebergebenen Typen
           setCellType((int)position.x, (int)position.z, type);
                
           //Schritt2: Überprüfe direkte Nachbarn
           int posX=((int)position.x);
           int posY=((int)position.z);
           System.out.println("PosX"+posX+"\n Position.x"+position.x);

           for(int i=-1;i<=1;i++)
            {
                for(int j=-1;j<=1;j++)
                {
                    checkNeighboursAt(posX+i, posY+j);
                    System.out.println("output:"+output);
                    if(output!=null)
                    {
                    mapNode.attachChild(output);
                    }
                }
            }
                        
                  
       }
       else
       {
           System.out.println("Bereits Gebaut!");
       }             
    }
    
    private void UpdateMap()
    {
        
    }
    
    private HashMap<String,Spatial> setMeshBox(int type)
    {
        switch (type)
        {
            case ValuesTerrain.HALL: 
                 return containerMapHandler.limbo;

          case ValuesTerrain.CAVEOFBEAST:
              return containerMapHandler.caveOfTheBeast;
//          case ValuesTerrain.CHAMBEROPLEASURE:
//          case ValuesTerrain.FARM:
                
            case ValuesTerrain.HALLOFANARCHY:
                return containerMapHandler.adamHall;
                
//           case ValuesTerrain.SOULCAMBER:
//           case ValuesTerrain.TOMBOFMEMORY:
//           case ValuesTerrain.WORKSHOP:
        }
        return containerMapHandler.limbo;
    }
    private void checkNeighboursAt(int x,int y)
    {
        System.out.println("CheckNeighBours At "+x+"Y:"+y);
        int type = terrainMap[x][y];
        HashMap<String,Spatial> meshBox = setMeshBox(type);
        
        System.out.println("Meshbox"+meshBox.toString());
        
        //Überprüfe nur, an der Stelle, wo Keine Erde und nicht das Höllenzentrum stehen
        if(type!=0&&type!=ValuesTerrain.HELLCENTER)
        {
            System.out.println("STARTE TEST");
         mapNode.detachChildNamed(name+""+x+""+y);       
        try
        {
        //GERADEN
        if((terrainMap[x+1][y]==type)&&(terrainMap[x-1][y]==type)&&(terrainMap[x][y+1]==ValuesTerrain.EARTH)&&(terrainMap[x][y-1]==ValuesTerrain.EARTH))
        {
                           
            System.out.println("X-Gerade");
            output = meshBox.get("straigt").clone();
            output.setName(name+""+x+""+y);
            
        }
        else if((terrainMap[x+1][y]==ValuesTerrain.EARTH)&&(terrainMap[x-1][y]==ValuesTerrain.EARTH)&&(terrainMap[x][y+1]==type)&&(terrainMap[x][y-1]==type))
        {
             System.out.println("Y-Gerade");
             output = meshBox.get("straigt").clone(); 
             output.rotate(0, degree, 0);
             output.setName(name+""+x+""+y);
        }

        //SEITEN
        
        else if( 
                (terrainMap[x-1][y-1]!=ValuesTerrain.EARTH)&&
                (terrainMap[x-1][y]!=ValuesTerrain.EARTH)&&
                (terrainMap[x-1][y+1]==ValuesTerrain.EARTH)&&
                
                (terrainMap[x][y-1]!=ValuesTerrain.EARTH)&&
                (terrainMap[x][y]!=ValuesTerrain.EARTH)&&
                (terrainMap[x][y+1]==ValuesTerrain.EARTH)&&
                
                (terrainMap[x+1][y-1]!=ValuesTerrain.EARTH)&&          
                (terrainMap[x+1][y]!=ValuesTerrain.EARTH)&&
                (terrainMap[x+1][y+1]==ValuesTerrain.EARTH)
                )
        {
             System.out.println("SEITE");
             output = meshBox.get("side").clone(); 
             //output.rotate(0, degree, 0);
             output.setName(name+""+x+""+y);
             
        }
   
        else if(
                (terrainMap[x-1][y-1]==ValuesTerrain.EARTH)&&
                (terrainMap[x-1][y]!=ValuesTerrain.EARTH)&&
                (terrainMap[x-1][y+1]!=ValuesTerrain.EARTH)&&
                
                (terrainMap[x][y-1]==ValuesTerrain.EARTH)&&
                (terrainMap[x][y]!=ValuesTerrain.EARTH)&&
                (terrainMap[x][y+1]!=ValuesTerrain.EARTH)&&
                
                (terrainMap[x+1][y-1]==ValuesTerrain.EARTH)&&          
                (terrainMap[x+1][y]!=ValuesTerrain.EARTH)&&
                (terrainMap[x+1][y+1]!=ValuesTerrain.EARTH))
        {
             System.out.println("SEITE");
             output = meshBox.get("side").clone(); 
             output.rotate(0, degree*2, 0);
             output.setName(name+""+x+""+y);
        }
       else if( (terrainMap[x-1][y-1]==ValuesTerrain.EARTH)&&
                (terrainMap[x-1][y]==ValuesTerrain.EARTH)&&
                (terrainMap[x-1][y+1]==ValuesTerrain.EARTH)&&
                
                (terrainMap[x][y-1]!=ValuesTerrain.EARTH)&&
                (terrainMap[x][y]!=ValuesTerrain.EARTH)&&
                (terrainMap[x][y+1]!=ValuesTerrain.EARTH)&&
                
                (terrainMap[x+1][y-1]!=ValuesTerrain.EARTH)&&          
                (terrainMap[x+1][y]!=ValuesTerrain.EARTH)&&
                (terrainMap[x+1][y+1]!=ValuesTerrain.EARTH))
        {
             System.out.println("SEITE");
             output = meshBox.get("side").clone(); 
             output.rotate(0, degree*3, 0);
             output.setName(name+""+x+""+y);
        }
       
       else if((terrainMap[x-1][y-1]!=ValuesTerrain.EARTH)&&
                (terrainMap[x-1][y]!=ValuesTerrain.EARTH)&&
                (terrainMap[x-1][y+1]!=ValuesTerrain.EARTH)&&
                
                (terrainMap[x][y-1]!=ValuesTerrain.EARTH)&&
                (terrainMap[x][y]!=ValuesTerrain.EARTH)&&
                (terrainMap[x][y+1]!=ValuesTerrain.EARTH)&&
                
                (terrainMap[x+1][y-1]==ValuesTerrain.EARTH)&&          
                (terrainMap[x+1][y]==ValuesTerrain.EARTH)&&
                (terrainMap[x+1][y+1]==ValuesTerrain.EARTH))
        {
             System.out.println("SEITE");
             output = meshBox.get("side").clone(); 
             output.rotate(0, degree, 0);
             output.setName(name+""+x+""+y);
        }
         

          
      
        //KURVEN
        else if((terrainMap[x][y-1]==type)&&(terrainMap[x+1][y]==type)&&(terrainMap[x-1][y]!=type)&&(terrainMap[x][y+1]!=type))
        {
            System.out.println("KURVE:-Y/+X");
            output = meshBox.get("curve").clone();
            output.setName(name+""+x+""+y);
        }
        
        else if((terrainMap[x][y-1]==type)&&(terrainMap[x+1][y]!=type)&&(terrainMap[x-1][y]==type)&&(terrainMap[x][y+1]!=type))
        {
            System.out.println("KURVE:+Y/-X/+90");
            output = meshBox.get("curve").clone();
            output.rotate(0,degree,0);
            output.setName(name+""+x+""+y);
        }
      
        else if((terrainMap[x][y-1]!=type)&&(terrainMap[x+1][y]!=type)&&(terrainMap[x-1][y]==type)&&(terrainMap[x][y+1]==type))
        {
            System.out.println("KURVE:Y/-X+180");
            output = meshBox.get("curve").clone();
            output.rotate(0,degree*2,0);
            output.setName(name+""+x+""+y);
        }
   
        else if((terrainMap[x][y-1]!=type)&&(terrainMap[x+1][y]==type)&&(terrainMap[x-1][y]!=type)&&(terrainMap[x][y+1]==type))
        {
            System.out.println("KURVE:+Y/+X+270");
            output = meshBox.get("curve").clone();
            output.rotate(0,degree*3,0);
            output.setName(name+""+x+""+y);
        }
      
        //3KREUZUNG
        else if((terrainMap[x-1][y]==type)&&
                (terrainMap[x][y-1]==type)&&
                (terrainMap[x+1][y]==type)&&
                (terrainMap[x][y+1]!=type)&&
                (terrainMap[x][y+1]==ValuesTerrain.EARTH))
        {

            System.out.println("Kreuzung 0");
            output = meshBox.get("3cross").clone();
            output.rotate(0,0,0);
            output.setName(name+""+x+""+y);
        }
        
                
        else if((terrainMap[x-1][y]==type)&&
                (terrainMap[x][y-1]!=type)&&
                (terrainMap[x+1][y]==type)&&
                (terrainMap[x][y+1]==type)&&
                (terrainMap[x][y-1]==ValuesTerrain.EARTH))
        {

            System.out.println("Kreuzung 180");
            output = meshBox.get("3cross").clone();
            output.rotate(0,degree*2,0);
            output.setName(name+""+x+""+y);
        }
        
        
        else if((terrainMap[x-1][y]==type)&&
                (terrainMap[x][y+1]==type)&&
                (terrainMap[x+1][y]!=type)&&
                (terrainMap[x][y+1]==type)&&
                (terrainMap[x+1][y]==ValuesTerrain.EARTH))
         {
            
            System.out.println("Kreuzung 270");
            output = meshBox.get("3cross").clone();
            output.rotate(0,degree*1,0);
            output.setName(name+""+x+""+y);

        }

        else if((terrainMap[x-1][y]!=type)&&
                (terrainMap[x][y+1]==type)&&
                (terrainMap[x+1][y]==type)&&
                (terrainMap[x][y+1]==type)&&
                (terrainMap[x-1][y]==ValuesTerrain.EARTH))
         {
            System.out.println("Kreuzung 90");
           output = meshBox.get("3cross").clone();
           output.rotate(0,degree*3,0);
           output.setName(name+""+x+""+y);

        }
    
        //4KREUZUNG
        else if((terrainMap[x-1][y]==type)&&(terrainMap[x][y-1]==type)&&(terrainMap[x+1][y]==type)&&(terrainMap[x][y+1]==type))
        {

            System.out.println("4Kreuzung");
            output = meshBox.get("4cross").clone();
            output.setName(name+""+x+""+y);

        }
        
        //Ende
        else if((terrainMap[x-1][y]==type)&&(terrainMap[x][y-1]!=type)&&(terrainMap[x+1][y]!=type)&&(terrainMap[x][y+1]!=type))
        {
            System.out.println("Ende 0");
            output = meshBox.get("end").clone();
            output.rotate(0,degree,0);
            output.setName(name+""+x+""+y);
        }
        else if((terrainMap[x-1][y]!=type)&&(terrainMap[x][y-1]==type)&&(terrainMap[x+1][y]!=type)&&(terrainMap[x][y+1]!=type))
        {
            System.out.println("Ende 90");
            output = meshBox.get("end").clone();
            output.setName(name+""+x+""+y);
            
        }
        else if((terrainMap[x-1][y]!=type)&&(terrainMap[x][y-1]!=type)&&(terrainMap[x+1][y]==type)&&(terrainMap[x][y+1]!=type))
        {
            System.out.println("Ende 180");
            output = meshBox.get("end").clone();
            output.rotate(0,degree*3,0);
            output.setName(name+""+x+""+y);
        }
        else if((terrainMap[x-1][y]!=type)&&(terrainMap[x][y-1]!=type)&&(terrainMap[x+1][y]!=type)&&(terrainMap[x][y+1]==type))
        {
            System.out.println("Ende 270");
            output = meshBox.get("end").clone();
            output.rotate(0,degree*2,0);
            output.setName(name+""+x+""+y);
        }
        
        else if(terrainMap[x][y-1]==2&&terrainMap[x+1][y]==type&&terrainMap[x][y+1]==type&&terrainMap[x-1][y]==type)
        {
            System.out.println("SIDE");
            output = meshBox.get("side").clone();
            //output.rotate(0,degree*3,0);
            output.setName(name+""+x+""+y);
        }

        else
        {
            System.out.println("No!");
            output = meshBox.get("4cross").clone();
            output.rotate(0,0, 0);
            output.setName(name+""+x+""+y);
        
        }
        
        
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Oh Dear!");
	output = meshBox.get("4cross").clone();
        output.setName(name+""+x+""+y);
        }
        output.move(x, 0, y);        
        System.out.println("Outline"+output.getTriangleCount());
        }     
    }
}
