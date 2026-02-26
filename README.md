# Allan DevOps Project

## Terminal History

```bash
# Initialize and configure Git
mkdir allan-devops-project
cd allan-devops-project
git init
git config --global user.name "Allan Rugamba"
git config --global user.email "allan.rugamba2023@kepler.org"

# Create initial commit
echo "# Allan DevOps Project" > README.md
git add README.md
git commit -m "chore: initial commit with README"
git branch -M main

# Add remote and push
git remote add origin https://github.com/AllanRugamba/Allan-devops-project.git
git push -u origin main

# Create dev branch
git checkout -b dev
git push -u origin dev

# Create and delete test branch
git checkout -b test
git push -u origin test
git push origin --delete test
git checkout dev
git branch -d test

# Create ft/setup branch
git checkout -b ft/setup

# Add test.java
cat > test.java << 'EOF'
public class Test {
    public static void main(String[] args) {
        System.out.println("Test setup for DevOps & CI/CD project");
    }
}
EOF

git add test.java
git commit -m "feat: add initial test.java setup"
git push -u origin ft/setup

# Git stash workflow
git stash save "Temporary changes: Maven setup"
git stash list
git add test.java pom.xml src/
git stash push -m "Maven project setup with dependencies"
git stash apply stash@{0}
git commit -m "feat: add Maven project with Selenium and TestNG dependencies"
git push origin ft/setup --force

# Merge conflict scenario
git checkout main
git add test.java
git commit -m "feat: add test.java in main branch"
git push origin main

git checkout ft/setup
git merge main
# Resolve conflict
git add test.java
git commit -m "fix: resolve merge conflict in test.java"
git push origin ft/setup
```
