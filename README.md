# Darts counter
This is a school project with the aim of learning to use android studio, kotlin and MVVM pattern. App was developed on virtual device Pixel 4 API 31.
## Application
This app manages score while playing darts. Features:
* 1 to 6 players can record score,
* initial score options are: 101, 201, 301, 501 and 701,
* saving unfinished game is possible,
* this game can be finished later, or deleted.

## Tracking the score
### Splash screen & Main Menu
Main menu has 2 options:
* Create new game
* Load some saved game

![splash]  ![menu]

### Create new game
You can create new game for 1 to 6 players with 5 possible initial scores. 

![create1] ![create2]

### Tracking score 
The player whose turn it is is marked.
If player scores more points than he needs, score remains the same and next player continues. Player that wins is shown in winning screen.

![fin1] ![fin2]

### Saved games
While playing, you can save game by clicking on save game button in the right upper corner. 
The game is then saved to Saved games and you can see the players and their score. 
You can finish saved game (player's turn is also saved) or delete it.

![save1] ![save2]



[splash]: https://live.staticflickr.com/65535/53780936102_cbaca82908.jpg
[menu]: https://live.staticflickr.com/65535/53780935667_baecc156ca.jpg
[create1]: https://live.staticflickr.com/65535/53782199549_26a546099c.jpg
[create2]: https://live.staticflickr.com/65535/53781957596_dca8780316.jpg
[in1]: https://live.staticflickr.com/65535/53782303020_7935bea581.jpg
[in2]: https://live.staticflickr.com/65535/53781880446_8aa2487e1c.jpg
[save1]: https://live.staticflickr.com/65535/53782305200_f94e9fcbb7.jpg
[save2]: https://live.staticflickr.com/65535/53780945177_d5f03a7e18.jpg
[fin1]: https://live.staticflickr.com/65535/53782319499_a5ea80b421.jpg
[fin2]: https://live.staticflickr.com/65535/53782096883_c62c87f126.jpg

