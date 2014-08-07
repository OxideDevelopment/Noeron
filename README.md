# Noeron

Noeron is a game where you must defeat oncoming waves of monsters. Zombies, Goos (More will be added later:exclamation:)
You can modify the terrain how ever you want, build a fortress, or a dungeon, you name it! ..Just don't die.

## Building Noeron

### Eclipse

Go to `File -> Import -> Gradle -> Gradle Project`, click Browse and navigate to the root folder of Noeron, then click `Build Model`. After a while, you'll see a root project and subprojects (android, core, desktop, html, ios). Select all the projects and click `Finish`. Note that this process can take a minute or two the first time you do it, as Gradle and some dependencies will be downloaded in the background.

Now, right click on `Noeron-Desktop`, and click `Run as -> Gradle build..`. You should see a text area. If you want to simply run the project, enter  `run`, click  `Apply -> Run `.

If you want an actual jar file, type `dist`. A jar file will be generated in `Noeron/desktop/build/libs/desktop-1.0.jar`.
