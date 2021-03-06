public class Runner
{
    public static void main (String [] args) {
        String response = "Y"; //Tracks player's response
        int errorResponse = 0; //Determines if player inputted something out of bounds
        String pattern = "a"; //Records the option player choice
        boolean notNum = true; //Determines if player inputted something out of bounds
        int dimensions = 20; //Default dimensions
        
        javax.swing.JOptionPane.showMessageDialog(null,"Welcome to the Game of Liiiiiiffffffe! Before we begin, here are a few tips: \n 1. In order to view cool patterns such as \"Gliders\", \"Spaceships\", etc., please input dimensions of at least 20 to prevent crashing the code (espeically for pulsar pattern)" +
                                                "\n 2. Holding down the mouse when clicking on a square will cause the square to change between black and white" +
                                                "\n 3. If a live square goes off the grid, the square will reappear on the other side of the grid (Wraparound technique)" +
                                                "\n 4. If you press \"Start\" without any of the squares being \"alive\", the code run assuming you made square \"alive\" already. Thus, please input squares first.");

        // Loop to remind user to enter "1" or "2" or "3" or "4" or "5"
        while(!(pattern.equals("1") || pattern.equals("2") || pattern.equals("3") || pattern.equals("4") || pattern.equals("5")))
        {
            if(errorResponse == 1)
            {
                javax.swing.JOptionPane.showMessageDialog(null,"Please enter a \"1\" or \"2\" or \"3\" or \"4\" or \"5\"");
            }
            pattern = javax.swing.JOptionPane.showInputDialog("Would you like to see the: \n 1. Glider \n 2. Spaceship \n 3. Pulsar \n 4. Random \n 5. Manual");
            errorResponse = 1;
        }  
        errorResponse = 0;
        while(notNum)
        {
            try
            {
                String dimensionResponse = javax.swing.JOptionPane.showInputDialog("What should be the dimension?");
                dimensions = Integer.parseInt(dimensionResponse);
                notNum = false;
            }
            catch (Exception e)
            {
                javax.swing.JOptionPane.showMessageDialog(null,"Please enter an integer");
                notNum = true;
            }
        }

        GoL test = new GoL(dimensions, pattern);
        test.setUp();
    }    
}
