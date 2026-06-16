## 📖 Panduan Kolaborasi Tim (Git & GitHub)

> **🚨 Aturan Emas Kerja Kelompok:** Selalu lakukan **Pull** (tarik data) sebelum mulai *coding*, dan lakukan **Commit & Push** (simpan & unggah) setelah selesai *coding* agar tidak terjadi bentrok (*conflict*).

---

### 💻 Bagian 1: Tutorial Push & Pull via Apache NetBeans

#### A. Cara Melakukan Pull (Mengambil Perubahan Terbaru)
Lakukan ini setiap kali kamu baru buka laptop untuk mengecek apakah ada *update* kodingan dari teman kelompok.
1. Di jendela **Projects** (kiri atas), klik kanan pada nama project kita (`restaurant_tidakpakaidollar`).
2. Pilih menu **Git** > **Remote** > **Pull...**
3. Pada jendela yang muncul, pastikan **Select Configured Repository** terpilih pada `origin`. Klik **Next**.
4. Centang branch `main`, lalu klik **Finish**. NetBeans akan mengunduh perubahan terbaru (jika ada).

#### B. Cara Melakukan Commit & Push (Menyimpan & Mengunggah Kode)
Lakukan ini setelah kamu selesai membuat fitur baru atau memperbaiki *bug*.
1. **Save** terlebih dahulu semua file yang kamu edit.
2. **Commit (Simpan Lokal):**
   * Klik kanan pada nama project > **Git** > **Commit...**
   * Pada kolom **Commit Message**, tulis pesan yang deskriptif (contoh: *"Menyelesaikan class model dan relasi DB"*).
   * Di bagian bawah, pastikan file-file yang kamu ubah tercentang.
   * Klik tombol **Commit**.
3. **Push (Unggah ke GitHub):**
   * Klik kanan lagi pada nama project > **Git** > **Remote** > **Push...**
   * Pilih `origin` dan klik **Next**.
   * Centang branch `main`, klik **Next**, lalu klik **Finish**.
   * *(Jika diminta login, masukkan username GitHub dan Personal Access Token)*.

---

### 🤝 Bagian 2: Tutorial Setup untuk Collaborators (Teman Kelompok)

Sebelum melakukan langkah di bawah, pastikan kamu sudah diundang sebagai kolaborator ke repository ini oleh pemilik project.

#### Langkah 1: Menerima Undangan
1. Buka email yang terdaftar di GitHub atau cek logo lonceng (**Notifications**) di pojok kanan atas website GitHub.
2. Buka notifikasi undangan repository, lalu klik **Accept Invitation**.

#### Langkah 2: Melakukan "Clone" Project via NetBeans
*Clone* adalah proses mengunduh project dari GitHub ke laptop masing-masing untuk pertama kalinya.
1. Buka aplikasi **Apache NetBeans**.
2. Di menu bar paling atas, klik menu **Team** > **Git** > **Clone...**
3. Pada jendela Clone Repository:
   * **Repository URL:** Masukkan link HTTPS project ini.
   * **User:** Isi dengan username GitHub kamu.
   * **Password:** Masukkan **Personal Access Token (PAT)** GitHub-mu.
   * Klik **Next**.
4. Centang branch `main` (atau `master`), lalu klik **Next**.
5. Pada bagian **Parent Directory**, pilih folder di laptopmu yang akan menjadi tempat penyimpanan project ini.
6. Klik **Finish**.

#### Langkah 3: Membuka Project
1. Tunggu beberapa saat sampai proses unduh selesai.
2. NetBeans biasanya akan memunculkan pop-up peringatan: *"Clone Completed. Do you want to open the project?"*
3. Klik **Open Project**.

🎉 **Selesai!** Sekarang kita semua sudah memiliki *source code* yang sama dan terhubung. Mari mulai bekerja menggunakan panduan **Push & Pull** di Bagian 1.

> **⚠️ Catatan Penting:** Jika kamu mengalami error otentikasi (*Authentication Failed*) saat proses Clone/Push, pastikan kamu sudah meng-generate **Personal Access Token (PAT)** melalui pengaturan akun GitHub kamu (`Settings` > `Developer settings` > `Personal access tokens`) untuk digunakan sebagai pengganti *password*.