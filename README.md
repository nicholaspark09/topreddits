# Reddit App
The reddit sample app by Nicholas Park for a Code Test

## Setup & Installation
1. Clone the repo with `git clone --recursive https://github.com/nicholaspark09/topreddits.git`

### Branching Strategy
* The `master` branch holds the current release
* The `development` branch hold what will be released
* Feature branches are created from `development`

### Feature Branches
1. check out the latest changes to `development` from `origin`: `git checkout development && git pull origin development`
2. create a new feature branch: `git checkout -b feature`
3. implement the feature and commit changes
4. at this point there might have been other changes merged to the `development` branch, so it's a good idea to rebase the feature branch.
  1. get the latest from `development`: `git checkout development && git pull origin development`
  2. rebase the `feature` branch against `development`: `git checkout feature && git rebase development`
  3. fix any rebase issues as they are presented, commit and continue the rebase until it's complete.
5. push the `feature` branch to `origin`: `git push origin feature`
6. open a pull request for the `feature` branch against `development` in github.
7. someone will review the code changes
8. fix potential code review issues, commit and push changes to origin: `git push origin feature`
9. repeat 7-8 until it's OK to merge the feature.
10. merge the Pull Request if it's possible, otherwise it's time for a new rebase:
  1. Follow steps 4.1-4.3
  2. Force push the changes to `origin`: `git push --force origin feature`
  3. Have reviewer validate that the rebase was performed correctly and then merge.