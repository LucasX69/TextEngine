# TextEngine
A text based game will be written to test the Engine that will be created.
This Engine should be editable for new games, so it should be as dynamic as possible.

The repository contains a "Game" and a "Engine" folder.
The Game folder will be used of the game and the Engine folder will be used for the Engine.
## The Game:
### Mechanics
The game will have a GUI.
This GUI will have a field with text, a sidebar, which may or may not be hide-able
This sidebar will have: save, load, "Journal" (something like that, which shows everything the player has encountered).
There may or may not be a map which the player can look at, it may simply be a description, like in Inform7 games.

### Story
The game will have a story, which is yet to be determined.
This game will not be published (most likely) as it is just a test to make sure the Engine is decent.
The story will probably be around a fantasy world, which includes a sense of Justice and Evil.
The protagonist of the story will either be Evil, Good or Neither.
There will be many different kinds of Species, including mythical creatures but also normal.
Species may or may not include Vampires, Humans, Dragons, Slimes, Werewolves or species kin to these.

## The Engine:
### Mechanics
#### Player
The player will be created on the start of the game.
Depending on the game, it is a custom or already determined character.
If the game decides that the person is static, the name of the .tec file should be "Player.tec"
The player should be in the middle of the story, but does not need to be the only playable character.
#### Notable Character
Normally in games/stories, there are notable people, these are defined as "Game.Characters".
They have a custom background, a static name and have an influence on the story to a certain degree.
These characters are created using a .tec file, which contains all their data.
Using a class that will read from that file and create the character whenever needed.
This way, you can easily change a character and add more characters to the game without needing to change the code.
There may be a mechanic for characters that are "special", that have custom skills.
This will then be done in the code by giving that character that special "Class".
#### Generic Character
There are also always "Generic Game.Characters", like "SoldierA" and "WizardB".
These characters will be given generic names, from an array of possible names.
These characters will be used in situations where you don't need to have a notable characters.
In games they are mostly used for random fight encounters.
#### Story Segments
These story segments may or may not be gotten from changeable files.
If the story is created using files, it will use the .tes (TextEngineStory) extension.
The engine will then need an interpreter for those files.
If the story is not created using .tes files, it will be done by hard-coding it in.

The displaying of the story will be done by creating a custom "Story" class.
This class (for example "GranadaCastleSiege.java) will have the story in it.
Further to be determined, based on decision on .tes files or not.

####TBA
### Reason
Half a year ago, I was thinking about making something like this, as I have seen many text games over the years.
For some reason, text games can be more satisfying than any other kinds of games.
Sure, visuals are always nice, but it's the same as "reading a book" VS "watching a movie".
Stories can be told way better, with just words.
Imagination can play a big role in how a story is perceived.
I am not a writer, but after I have made the engine work, I may actually try something with it.
