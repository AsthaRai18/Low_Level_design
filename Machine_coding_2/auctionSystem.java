// A company FooBar has hosted an Online Auction System where any User(seller) can sell an object through an auction.
// Each auction has a lowest bid limit and the highest bid limit.
// Any registered user(buyer) can participate in an auction and bid on the product. He/She can update this bid amount or withdraw from an auction until the auction is completed. There is a meager participation cost of Rs. x for participating in an auction.

// The Seller is given 20% of the participation cost(x). Rest remains as commission for FooBar.

// When the auction closes, show the winning bid using the highest unique bid.
// For a seller, show the profit/loss. profit/loss = winning auction price + participation cost-share(no_of_bidders * 0.2 * participation cost) - an average of the lowest and highest bid limits
// If there is no winner then the profit will only be from participation cost.

// The program should take as input two or more auctions and a set of users participating in these. Multiple auctions can happen simultaneously.

// Highest Unique bid definition:
// For a set of users A, B, C, D, E, F participating in auction A1

// A bids 50,
// B bids 90,
// C bids 100,
// D bids 90,
// E bids 70,
// F bids 100
// Here 70 is the highest unique bid, therefore E is the winner. If there is no highest unique bid by the end of the auction, there is no winner for the auction.

// Bonus:
// Upgrade the buyer to a preferred buyer if he has participated in more than 2 auctions in the past. And for choosing a winner, whenever there is a tie on the highest bid, preference should be given to the preferred buyer and if it's tied between multiple preferred buyers, fallback to the next highest bid.
// The preferred buyer is across sellers on the platform.
// Functionalities expected:
// • Add Buyer(name)
// • Add Seller(name)
// • Create Auction(id, lowest bid limit, highest bid limit, partiticipation_cost, seller)
// • Create/Update Bid(buyer, auction, amount)
// • Withdraw bid(buyer, auction)
// • Close auction and return winning bid
// • Get profit/loss(seller, auction)

// Sample Test cases:
// Test case 1:
// • ADD_BUYER("buyer1")
// • ADD_BUYER("buyer2")
// • ADD_BUYER("buyer3")
// • ADD_SELLER("seller1")
// • CREATE_AUCTION ("A1", "10", "50", "1", "seller1")
// • CREATE_BID("buyer1", "A1", "17")
// • CREATE_BID("buyer2", "A1", "15")
// • UPDATE_BID("buyer2", "A1", "19")
// • CREATE_BID("buyer3", "A1", "19")
// • CLOSE_AUCTION("A1") // Should give Buyer1 as winner
// • GET_PROFIT("seller1", "A1") // (17 + (3 * 0.2 * 1) - 30) = -12.4

// Test case 2:
// • ADD_SELLER("seller2")
// • CREATE_AUCTION("A2", "5", "20", "2", "seller2")
// • CREATE_BID("buyer3", "A2", 25) //This should fail as highest bid limit is 20 for A2
// • CREATE_BID("buyer2, "A2", 5)
// • WITHDRAW_BID("buyer2", "A2")
// • CLOSE_AUCTION("A2") // No winner
// • GET_PROFIT("seller2", "A2") // (1 * 0.2 * 2) = 0.4 only consider profit from participation cost

// Expectations:
// Create the sample data yourself. You can put it into a file, test case or main driver program itself.
// The code should be demo-able. Either by using the main driver program or test cases.
// The code should be modular. The code should have the basic OO design. Please do not jam in the responsibilities of one class into another.
// The code should be extensible. Wherever applicable, use interfaces and contracts between different methods. It should be easy to add/remove functionality without rewriting the entire codebase.
// The code should handle edge cases properly and fail gracefully.
class User{
    String name;
}
class Buyer extends User
{
    Map<Auction,Bid>mp;
    
}
class Seller extends User{
    Double amount;

}
class Auction 
{
    String name;
    Integer lowest_bid_limit;
    Integer highest_bid_limit;
    Boolean isClosed;
    List<Buyer>buyers;
    List<Bid>bidsSet;
    public Auction(String name, String lowest_bid_limit, String highest_bid_limit)
    {
        
    }
}
class Bid{
    Buyer buyer;
    Auction auction;
    Double value;
}
class FooBar{
    Map<String,Buyer>buyer_map;
    Map<String,Seller>seller_map;
    Map<String,Auction>acution_map;
    Map<Buyer,Map<Auction,Bid>>bidsList;
        // buyer -> auction -> 
    public void addBuyer()
    {

    }
    public void addSeller()
    {

    }
    public void createAuction()
    {

    }
    public void createBid(String buyer,String auction, double bidValue)
    {
        Buyer b = buyer_map.get(buyer);
        Auction a = acution_map.get(auction);
        Map<Auction,Bid>mp = new HashMap<>();
        Bid bid = new Bid(b,a,bidValue);
        mp.put(a,bid);
        bidsList.put(b,a);
        a.buyers.add(b);
    }
    public void updateBid(String buyer,String auction)
    {
        Buyer b = buyer_map.get(buyer);
        Auction a = acution_map.get(auction);
        Bid bid = new Bid(b,a,bidValue);
    }
    public void withdrawBid()
    {

    }
    public void closeAuction(String a)
    {
        isClosed = true;
        Map<Double, Integer> freq = new HashMap<>();
        for (Bid bid : bids.values()) {
            freq.put(bid.value, freq.getOrDefault(bid.value,0)+1);
        }
        Auction a = acution_map.get(a);
        List<Bids>bidsList = a.bidsSet;
        Set<Integer>st;
        for(int i = 0;i<bidsList.size();i++)
        {
            st.add(bidsList.get(i).value);
        }
        bidsList.sort((b1, b2) -> Double.compare(b2.value, b1.value));
        Bid maxBid = new Bid();
        for(int i = 0;i<bidsList.size();i++)
        {
            if(!st.contains(bidsList.get(i)))
            {
                maxBid = bidsList.get(i);
            }
        }
    }
    public void getProfit()
    {

    }
}
public static void main(String[] args)
{
    Foobar FooBar = new FooBar();
    try(BufferedReader br = new BufferedReader(new FileReader("input.txt")))
    {
        String line;
        while((line=br.readLine())!=null)
        {
            processCommand(line,FooBar);
        }
    }catch(IOException e)
    {
        e.printStackTrace();
    }

}
static void processCommand(String line , Foobar foo){
    String []parts = line.split(" ");
    switch(parts[0])
    {
        case "AUCTION_":

        default:

    }
}
