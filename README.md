# Live Football World Cup Scoreboard library that shows all the ongoing matches and their scores.

The scoreboard supports the following operations:
1. Start a new match, assuming initial score 0 – 0 and adding it the scoreboard.
   This should capture following parameters:  <br>
    a. Home team <br>
    b. Away team
2. Update score. This should receive a pair of absolute scores: home team score and away
   team score.
3. Finish match currently in progress. This removes a match from the scoreboard.
4. Get a summary of matches in progress ordered by their total score. The matches with the
   same total score will be returned ordered by the most recently started match in the
   scoreboard.

For example, if following matches are started in the specified order and their scores
respectively updated
1. Mexico 0 - Canada 5
2. Spain 10 - Brazil 2 
3. Germany 2 - France 2 
4. Uruguay 6 - Italy 6 
5. Argentina 3 - Australia 1 

The summary should be as follows:
1. Uruguay 6 - Italy 6
2. Spain 10 - Brazil 2
3. Mexico 0 - Canada 5
4. Argentina 3 - Australia 1
5. Germany 2 - France 2

# Notes
- no fireworks here, I was told that it must be simple, so it is very simple
- I'm usually using master, develop and feature branches, but since this is only my branch I left here "main" which was suggested by github. I feel a little wierd about it, but not wierd enough to change it - it is only an exercise after all. 