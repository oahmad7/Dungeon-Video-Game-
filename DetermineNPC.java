/*
 * 
 * Author: Martin Bautista
 * NetID: Mbauti23
 * UIN: 663597360
 * 
 * The Determination of an NPC via keywords
 */

public class DetermineNPC 
{
    private int wellMannered = 0;
    private int illMannered = 0;
    
    //NPC types and their respective numbers
    //0 = Friendly
    //1 = Helpful
    //2 = Mischevious
    //3 = Aggressive
    //4 = Chaotic
    private int typeOfNPC = 0;
    
    //determines a personality for a new random NPC
    public DetermineNPC() 
    {
        typeOfNPC = (int )(Math.random() * 4 + 0);
    }
    
    //determines the personality for a given NPC
    public DetermineNPC(String desc) 
    {
    
      for (String word : desc.split("\\s+"))
      {
        if (word.toLowerCase().contains("good"))
          wellMannered++;
        if (word.toLowerCase().contains("cheerful"))
          wellMannered++;
        if (word.toLowerCase().contains("well-mannered"))
          wellMannered++;
        if (word.toLowerCase().contains("friendly"))
          wellMannered++;
        if (word.toLowerCase().contains("polite"))
          wellMannered++;
        if (word.toLowerCase().contains("civil"))
          wellMannered++;
      
        if (word.toLowerCase().contains("bad"))
          illMannered++;
        if (word.toLowerCase().contains("ill-mannered"))
          illMannered++;
        if (word.toLowerCase().contains("angry"))
          illMannered++;
        if (word.toLowerCase().contains("upset"))
          illMannered++;
        if (word.toLowerCase().contains("mean"))
          illMannered++;
        if (word.toLowerCase().contains("aggressive"))
          illMannered++;
      }
      
      if((illMannered == 0 && wellMannered == 0) || (illMannered == wellMannered))
        typeOfNPC = (int )(Math.random() * 4 + 0);
      else if(illMannered > wellMannered)
        typeOfNPC = (int )(Math.random() * 4 + 1);
      else if(illMannered < wellMannered)
        typeOfNPC = (int )(Math.random() * 1 + 0);
    }
    
    public int getTypeOfNPC()
    {
      return typeOfNPC;
    }
}
    
   