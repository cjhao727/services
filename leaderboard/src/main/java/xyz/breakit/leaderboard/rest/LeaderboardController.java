package xyz.breakit.leaderboard.rest;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import xyz.breakit.leaderboard.service.ImmutableLeaderboardEntry;
import xyz.breakit.leaderboard.service.LeaderboardEntry;
import xyz.breakit.leaderboard.service.LeaderboardService;

import java.util.List;

@RestController
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }


    @GetMapping("/top/{k}")
    public Mono<List<LeaderboardEntry>> top10(@PathVariable int k) {
        return Mono.just(leaderboardService.getTopScores(k));
    }

    @PostMapping(name = "/scores/*", consumes = "application/json")
    public void submit(@RequestBody ImmutableLeaderboardEntry score) {

    }

}
