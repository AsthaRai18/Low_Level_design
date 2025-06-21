import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        FooBar foo = new FooBar();

        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                processCommand(line, foo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processCommand(String command, FooBar foo) {
        String[] parts = command.split(" ");

        switch(parts[0]) {
            case "ADD_BUYER":
                foo.addBuyer(parts[1]);
                break;

            case "ADD_SELLER":
                foo.addSeller(parts[1]);
                break;

            case "CREATE_AUCTION":
                // CREATE_AUCTION id low high cost seller
                foo.createAuction(parts[1], 
                                  Integer.parseInt(parts[2]), 
                                  Integer.parseInt(parts[3]), 
                                  Double.parseDouble(parts[4]), 
                                  parts[5]);
                break;

            case "CREATE_BID":
                foo.createOrUpdateBid(parts[1], parts[2], Double.parseDouble(parts[3]));
                break;

            case "UPDATE_BID":
                foo.createOrUpdateBid(parts[1], parts[2], Double.parseDouble(parts[3]));
                break;

            case "WITHDRAW_BID":
                foo.withdrawBid(parts[1], parts[2]);
                break;

            case "CLOSE_AUCTION":
                foo.closeAuction(parts[1]);
                break;

            case "GET_PROFIT":
                foo.getProfit(parts[1], parts[2]);
                break;

            default:
                System.out.println("Unknown command: " + command);
        }
    }
}
