1. Cd to the folder "saurabh-testcases" where src and test folder exist
2. Create build directory if not exists.
3. Compile the src code, this compiles the model classes:
javac -d build/ src/com/eteks/sweethome3d/model/*.java
4. Compile the test code:
javac -classpath build -d build test/saurabh/TestLabel.java
5. Run the Test class:
java -classpath build/ test.saurabh.TestLabel

