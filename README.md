# RPGCharacters

Simple command line app that creates and rpg character and lets you improve it by leveling up and gaining new equipment.

## 1. Usage

```
 git clone
``` 

You can run the app in IDE of your choice. 

## 2. Classes

Main classes that contain actual methods.

- ```Hero```
    - Is an extension of ```Character``` class. Contains methods to equip items and calculate attributes.
- ```Item```
    - Is extended by Weapon and Armor.
    - Both Weapon and Armor contain random item generation. 
- ```Main```
    - Initial commands and "playing the game" are located here.
- ```Writer```
    - Does most of the output of the app.

## 3. Methods

- ```levelUp()```
    - Increases the character level by 1, and updates the base stats according to the selected "class".
- ```createItem()```
    - Creates a random level 1-60 weapon or armor item, with random stats.
- ```equipItem(Item item)```
    - Checks if item is valid and equips it if true. If false throws an error.
    
## 4. Maintainer

[Sasu Korhonen](https://github.com/bgf122)