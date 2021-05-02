# Homework 9

1. Follow instructions in [wiki](https://gitlab.fi.muni.cz/grp-pv256/wiki/wikis/home)
and proceed with forking this repo
2. Make `Character` class to be a DB entity
3. Finish implementation of `CharacterDao` and `MortyDatabase`
   * Use function names, which are already in use in `CharacterRepository`
   * In `CharacterDao`, implement function for entity deletion (even if it's not used)
4. Finish implementation of `getCharacter` function in `CharacterRepository`
5. Submit changes into a **submit** branch and push it to your forked repo
6. Create a merge request against _your_ master branch and assign it to _qtokar_
Your commit must pass Gitlab CI verification. To test this locally, run:
```
./gradlew check
```
Note: Pipelines are not passing in the initial state.
