package auction_bidding;
import java.util.*;
// • Add Buyer(name)
// • Add Seller(name)
// • Create Auction(id, lowest bid limit, highest bid limit, partiticipation_cost, seller)
// • Create/Update Bid(buyer, auction, amount)
// • Withdraw bid(buyer, auction)
// • Close auction and return winning bid
// • Get profit/loss(seller, auction)

class User
{
    String name;
    User(String name)
    {
        this.name = name;
    }
}
class Auction{
    int id;
    int lowest_bid_limit;
    int highest_bid_limit;
    double partiticipation_cost;
    User seller;
    Boolean isAuctionClosed;
    List<Bid>bids;
    Auction(int id,int lowest_bid_limit,int highest_bid_limit, double partiticipation_cost,User seller)
    {
        this.id = id;
        this.lowest_bid_limit = lowest_bid_limit;
        this.highest_bid_limit = highest_bid_limit;
        this.partiticipation_cost = partiticipation_cost;
        this.seller = seller;
    }
}
class Bid implements Comparable<Bid>{
    Auction auction;
    User buyer;
    Double amount;
    Bid(Auction auction, User buyer,Double amount)
    {
        this.amount = amount;
    }
    @Override
    public int compareTo(Bid o)
    {
        return Double.compare(this.amount, o.amount);
    }
}
class AuctionService
{

}
public class auction {
    
}
