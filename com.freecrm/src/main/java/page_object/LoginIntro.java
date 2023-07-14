package page_object;

public class LoginIntro {
    PageFactory.initElements(driver, this);
}

    public void navigateToLoginPage() {
        private void navigateToLoginPage() {
            clickOnElement(loginPageLink);
        }

        public void sendKeysEmailAdd(String email) {
            private void sendKeysEmailAdd(String email) {
                isElementVisible(myEmailAddress);
                clickOnElement(myEmailAddress);
                sendKeysToElement(myEmailAddress, email);
            }

            public void sendKeysPassword(String password) {
                private void sendKeysPassword(String password) {
                    isElementVisible(myPassword);
                    clickOnElement(myPassword);
                    sendKeysToElement(myPassword, password);
                }

                public void clickLoginButton() {
                    private void clickLoginButton() {
                        clickOnElement(loginButton);
                    }

                    public MainNavigation loginToAccount(String email, String password){
                        // navigateToLoginPage();
                        sendKeysEmailAdd(email);
                        sendKeysPassword(password);
                        clickLoginButton();
                        return new MainNavigation();
                    }




                }
}
