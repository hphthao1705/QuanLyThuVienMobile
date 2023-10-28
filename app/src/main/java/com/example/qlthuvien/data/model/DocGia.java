package com.example.qlthuvien.data.model;

public class DocGia {
        private int id_dg;
        private int id_sv;
        private String email;
        private int password;

        public int getId_dg() {
            return id_dg;
        }

        public void setId_dg(int id_dg) {
            this.id_dg = id_dg;
        }

        public int getId_sv() {
            return id_sv;
        }

        public void setId_sv(int id_sv) {
            this.id_sv = id_sv;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getPassword() {
            return password;
        }

        public void setPassword(int password) {
            this.password = password;
        }

        public DocGia(int id_dg, int id_sv, String email, int password) {
            this.id_dg = id_dg;
            this.id_sv = id_sv;
            this.email = email;
            this.password = password;
        }
}
