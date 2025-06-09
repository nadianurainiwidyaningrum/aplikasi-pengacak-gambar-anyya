import ads_mobile_sdk.h6
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp()
        }
    }
}

@Composable
fun MainApp() {
    var screen by remember { mutableStateOf("search") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Sleman CarePlus") })
        },
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Search, null) },
                    label = { Text("Cari Data") },
                    selected = screen == "search",
                    onClick = { screen = "search" }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Description, null) },
                    label = { Text("Pengajuan") },
                    selected = screen == "application",
                    onClick = { screen = "application" }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.ReportProblem, null) },
                    label = { Text("Pengaduan") },
                    selected = screen == "complaint",
                    onClick = { screen = "complaint" }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.History, null) },
                    label = { Text("Riwayat") },
                    selected = screen == "history",
                    onClick = { screen = "history" }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Event, null) },
                    label = { Text("Jadwal") },
                    selected = screen == "schedule",
                    onClick = { screen = "schedule" }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Place, null) },
                    label = { Text("Agen") },
                    selected = screen == "agent",
                    onClick = { screen = "agent" }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Help, null) },
                    label = { Text("Panduan") },
                    selected = screen == "guide",
                    onClick = { screen = "guide" }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.BarChart, null) },
                    label = { Text("Statistik") },
                    selected = screen == "stats",
                    onClick = { screen = "stats" }
                )
            }
        }z
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (screen) {
                "search" -> ScreenCariData()
                "application" -> ScreenPengajuan()
                "complaint" -> ScreenPengaduan()
                "history" -> ScreenRiwayat()
                "schedule" -> ScreenJadwal()
                "agent" -> ScreenAgen()
                "guide" -> ScreenPanduan()
                "stats" -> ScreenStatistik()
            }
        }
    }
}



}

@Composable
fun ScreenCariData() {
    var search by remember { mutableStateOf("") }
    val dataWarga = listOf(
        "Andi - 123456",
        "Sari - 654321",
        "Budi - 112233"
    )
    val hasil = dataWarga.filter { it.contains(search, ignoreCase = true) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Cari Data Warga Sleman CarePlus", style = MaterialTheme.typography.h6)
        Spacer(Modifier.height(8.dp))
        TextField(value = search, onValueChange = { search = it }, label = { Text("NIK atau Nama") })
        Spacer(Modifier.height(8.dp))
        if (hasil.isEmpty()) {
            Text("Tidak ada data ditemukan")
        } else {
            for (item in hasil) {
                Text(item, modifier = Modifier.padding(4.dp))
            }
        }
    }
}

@Composable
fun ScreenPengajuan() {
    var nik by remember { mutableStateOf("") }
    var jenis by remember { mutableStateOf("") }
    var submitted by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Pengajuan Bantuan Sleman CarePlus", style = MaterialTheme.typography.h6)
        Spacer(Modifier.height(8.dp))
        TextField(value = nik, onValueChange = { nik = it }, label = { Text("NIK") })
        Spacer(Modifier.height(8.dp))
        TextField(value = jenis, onValueChange = { jenis = it }, label = { Text("Jenis Bantuan") })
        Spacer(Modifier.height(8.dp))
        Button(onClick = { submitted = true }, enabled = nik.isNotBlank() && jenis.isNotBlank()) {
            Text("Ajukan")
        }
        Spacer(Modifier.height(8.dp))
        if (submitted) {
            Text("Pengajuan berhasil dikirim")
        }
    }
}

@Composable
fun ScreenPengaduan() {
    var nik by remember { mutableStateOf("") }
    var isi by remember { mutableStateOf("") }
    var submitted by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Pengaduan Sleman CarePlus", style = MaterialTheme.typography.h6)
        Spacer(Modifier.height(8.dp))
        TextField(value = nik, onValueChange = { nik = it }, label = { Text("NIK") })
        Spacer(Modifier.height(8.dp))
        TextField(value = isi, onValueChange = { isi = it }, label = { Text("Isi Pengaduan") })
        Spacer(Modifier.height(8.dp))
        Button(onClick = { submitted = true }, enabled = nik.isNotBlank() && isi.isNotBlank()) {
            Text("Kirim Pengaduan")
        }
        Spacer(Modifier.height(8.dp))
        if (submitted) {
            Text("Pengaduan berhasil dikirim")
        }
    }
}

@Composable
fun ScreenRiwayat() {
    val riwayatBantuan = listOf(
        "Bantuan Sosial - Diterima",
        "Bantuan Pendidikan - Ditolak"
    )
    val riwayatPengaduan = listOf(
        "Lampu Jalan Rusak - Proses",
        "Sampah Menumpuk - Selesai"
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Riwayat Bantuan dan Pengaduan Sleman CarePlus", style = MaterialTheme.typography.h6)
        Spacer(Modifier.height(8.dp))
        Text("Riwayat Bantuan:")
        for (item in riwayatBantuan) {
            Text("- $item", modifier = Modifier.padding(4.dp))
        }
        Spacer(Modifier.height(8.dp))
        Text("Riwayat Pengaduan:")
        for (item in riwayatPengaduan) {
            Text("- $item", modifier = Modifier.padding(4.dp))
        }
    }
}

@Composable
fun ScreenJadwal() {
    val jadwal = listOf(
        "10 Juni 2025 - Balai Desa Sleman",
        "15 Juni 2025 - Balai Desa Gamping"
    )
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Jadwal Penyaluran Sleman CarePlus", style = MaterialTheme.typography.h6)
        Spacer(Modifier.height(8.dp))
        for (item in jadwal) {
            Text(item, modifier = Modifier.padding(4.dp))
        }
    }
}

@Composable
fun ScreenAgen() {
    val context = LocalContext.current
    val agen = listOf(
        Triple("Agen Sleman", -7.743, 110.377),
        Triple("Agen Gamping", -7.780, 110.360),
        Triple("Agen Depok", -7.760, 110.390)
    )
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Agen Penyalur Sleman CarePlus", style = MaterialTheme.typography.h6)
        Spacer(Modifier.height(8.dp))
        for (item in agen) {
            Text(
                "${item.first} - Tap untuk lihat lokasi",
                modifier = Modifier
                    .padding(4.dp)
                    .clickable {
                        val gmmIntentUri = Uri.parse("geo:${item.second},${item.third}?q=${Uri.encode(item.first)}")
                        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                        mapIntent.setPackage("com.google.android.apps.maps")
                        context.startActivity(mapIntent)
                    }
            )
        }
    }
}

@Composable
fun ScreenPanduan() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Panduan Penggunaan Sleman CarePlus", style = MaterialTheme.typography.h6)
        Spacer(Modifier.height(8.dp))
        Text("1. Cari data warga pakai NIK atau nama.")
        Text("2. Ajukan bantuan dengan mengisi formulir.")
        Text("3. Kirim pengaduan jika ada masalah.")
        Text("4. Cek riwayat bantuan dan pengaduan.")
        Text("5. Lihat jadwal penyaluran bantuan.")
        Text("6. Cari agen dan buka lokasi di Google Maps.")
        Text("7. Lihat statistik bantuan.")
    }
}

@Composable
fun ScreenStatistik() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Statistik Bantuan Sleman CarePlus", style = MaterialTheme.typography.h6)
        Spacer(Modifier.height(8.dp))
        Text("Total pengajuan: 100")
        Text("Pengajuan diterima: 70")
        Text("Pengajuan ditolak: 30")
        Text("Pengaduan masuk: 40")
    }
}

// Preview semua screen untuk Android Studio preview
@Preview(showBackground = true)
@Composable
fun PreviewCariData() {
    ScreenCariData()
}

@Preview(showBackground = true)
@Composable
fun PreviewPengajuan() {
    ScreenPengajuan()
}

@Preview(showBackground = true)
@Composable
fun PreviewPengaduan() {
    ScreenPengaduan()
}

@Preview(showBackground = true)
@Composable
fun PreviewRiwayat() {
    ScreenRiwayat()
}

@Preview(showBackground = true)
@Composable
fun PreviewJadwal() {
    ScreenJadwal()
}

@Preview(showBackground = true)
@Composable
fun PreviewAgen() {
    ScreenAgen()
}

@Preview(showBackground = true)
@Composable
fun PreviewPanduan() {
    ScreenPanduan()
}

@Preview(showBackground = true)
@Composable
fun PreviewStatistik() {
    ScreenStatistik()
}
