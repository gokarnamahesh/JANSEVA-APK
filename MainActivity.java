package com.janseva.publichelping;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.*;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private final int navy = Color.rgb(17, 48, 87);
    private final int blue = Color.rgb(24, 104, 190);
    private final int pale = Color.rgb(239, 246, 253);
    private final ArrayList<String> complaints = new ArrayList<>();
    private LinearLayout page;
    private int nextId = 1001;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showHome();
    }

    private TextView text(String value, int size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return t;
    }

    private LinearLayout vertical() {
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setPadding(20, 16, 20, 20);
        l.setBackgroundColor(Color.WHITE);
        return l;
    }

    private void base(String subtitle) {
        ScrollView scroll = new ScrollView(this);
        page = vertical();
        scroll.addView(page);
        setContentView(scroll);

        LinearLayout header = vertical();
        header.setBackgroundColor(navy);
        header.setPadding(22, 22, 22, 22);
        header.addView(text("JANSEVA", 25, Color.WHITE, true));
        header.addView(text("Your Voice, Your City", 14, Color.WHITE, false));
        page.addView(header, new LinearLayout.LayoutParams(-1, -2));
        TextView sub = text(subtitle, 21, navy, true);
        sub.setPadding(0, 22, 0, 12);
        page.addView(sub);
    }

    private void addButton(String label, Runnable action) {
        Button b = new Button(this);
        b.setText(label);
        b.setTextColor(Color.WHITE);
        b.setAllCaps(false);
        b.setTextSize(16);
        b.setBackgroundTintList(android.content.res.ColorStateList.valueOf(blue));
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-1, -2);
        p.setMargins(0, 10, 0, 6);
        page.addView(b, p);
        b.setOnClickListener(v -> action.run());
    }

    private void addCard(String title, String body) {
        LinearLayout card = vertical();
        card.setBackgroundColor(pale);
        card.addView(text(title, 17, navy, true));
        TextView d = text(body, 14, Color.DKGRAY, false);
        d.setPadding(0, 6, 0, 0);
        card.addView(d);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-1, -2);
        p.setMargins(0, 7, 0, 7);
        page.addView(card, p);
    }

    private void showHome() {
        base("Welcome to JANSEVA");
        addCard("Report a civic issue", "Report problems such as damaged roads, water supply, garbage, or street lights.");
        addCard("Track your complaint", "View the demo complaint status and reference number.");
        addCard("College project demo", "This sample works offline. Complaints are stored only while the app remains open.");
        addButton("Report a Complaint", this::showReport);
        addButton("Track Complaints", this::showComplaints);
        addButton("About JANSEVA", this::showAbout);
    }

    private void showReport() {
        base("Report a Complaint");
        page.addView(text("Choose department", 15, navy, true));
        Spinner department = new Spinner(this);
        String[] departments = {"Roads", "Water Supply", "Garbage Collection", "Street Lights", "Drainage", "Other"};
        department.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, departments));
        page.addView(department);

        page.addView(text("Location / Area", 15, navy, true));
        EditText location = new EditText(this);
        location.setHint("Example: Near college, Belagavi");
        page.addView(location);

        page.addView(text("Describe the issue", 15, navy, true));
        EditText description = new EditText(this);
        description.setHint("Explain the civic problem...");
        description.setMinLines(4);
        description.setGravity(Gravity.TOP);
        page.addView(description);

        addButton("Submit Complaint", () -> {
            String loc = location.getText().toString().trim();
            String desc = description.getText().toString().trim();
            if (loc.isEmpty() || desc.isEmpty()) {
                Toast.makeText(this, "Please enter location and issue details.", Toast.LENGTH_LONG).show();
                return;
            }
            String id = "JS-" + nextId++;
            complaints.add(0, id + " | " + department.getSelectedItem() + " | " + loc + "\n" + desc + "\nStatus: Submitted");
            new android.app.AlertDialog.Builder(this)
                .setTitle("Complaint submitted")
                .setMessage("Your demo reference number is " + id)
                .setPositiveButton("OK", (dialog, which) -> showComplaints())
                .show();
        });
        addButton("Back to Home", this::showHome);
    }

    private void showComplaints() {
        base("My Complaints");
        if (complaints.isEmpty()) {
            addCard("No complaints yet", "Submit a demo complaint to see it listed here.");
        } else {
            for (String item : complaints) addCard("Complaint record", item);
        }
        addButton("Report another issue", this::showReport);
        addButton("Back to Home", this::showHome);
    }

    private void showAbout() {
        base("About the Project");
        addCard("JANSEVA – Public Grievance Application", "A student prototype concept for helping citizens report civic issues and follow their progress.");
        addCard("Demo limitations", "This version does not connect to a government department, server, GPS, AI service, or notification system. It stores sample data only while the app remains open.");
        addButton("Back to Home", this::showHome);
    }
}
