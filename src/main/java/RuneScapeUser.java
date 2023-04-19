public class RuneScapeUser {
        private int id;
        private String username;
        private String email;
        private String password;
        private String birthDate;

    @Override
    public String toString() {
        return "RuneScapeUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }

    public RuneScapeUser(int id, String username, String email , String password , String birthDate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.birthDate = birthDate;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RuneScapeUser() {
    }

    public boolean isPasswordValid() {
        boolean containsUppercase = false;
        boolean containsLowercase = false;
        boolean containsDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                containsUppercase = true;
            } else if (Character.isLowerCase(c)) {
                containsLowercase = true;
            } else if (Character.isDigit(c)) {
                containsDigit = true;
            }
        }
        return containsUppercase && containsLowercase && containsDigit && password.length() >=11;
    }
    public static boolean isValid(String Password) {
        if (Password.length() >= 5 && Password.length() <= 10) {
            return true;
        } else
            return false;
    }
    }

