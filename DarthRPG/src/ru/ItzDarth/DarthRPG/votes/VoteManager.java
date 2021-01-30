package ru.ItzDarth.DarthRPG.votes;

import ru.ItzDarth.DarthRPG.AbstractManagerRPG;
import ru.ItzDarth.DarthRPG.SakiRPG;

public class VoteManager extends AbstractManagerRPG {

    //private static final String VOTE_MESSAGE = ChatColor.GRAY + "> " + ChatColor.GREEN + "Thanks for voting! Use your Reward Points with " + ChatColor.YELLOW + "/rewards" + ChatColor.GREEN + ".";

    public VoteManager(SakiRPG plugin) {
        super(plugin);
    }

    @Override
    public void initialize() {
    }

    /*@EventHandler(priority = EventPriority.NORMAL)
    public void onVotifierEvent(VotifierEvent event) {
        Vote vote = event.getVote();
        System.out.println("Received vote: " + vote);
        String name = vote.getUsername();
        Runnable callback = () -> {
            System.out.println("Processed vote for " + name + " from " + vote.getServiceName() + ".");
        };
        RewardsManager.givePoints(name, 1, VOTE_MESSAGE, callback);
    }*/

}
