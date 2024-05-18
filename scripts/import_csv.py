#!/usr/bin/python3

import csv
import sqlite3

# Define variables for database and CSV file paths
DATABASE_FILE = 'players.db'
CSV_FILE = 'Player.csv'

# Initialize counters
total_rows = 0
inserted_rows = 0
failed_rows = 0


def insert_player_data(row):
    """Inserts a single player data row into the database.

  Args:
      row (list): A list containing player data.

  Returns:
      bool: True if insertion is successful, False otherwise.
  """
    try:
        cursor.execute(insert_query, row)
        return True
    except sqlite3.Error as e:
        print(f"Error inserting row: {e}")
        return False


# Connect to the database
conn = sqlite3.connect(DATABASE_FILE)
cursor = conn.cursor()

# Create table
cursor.execute('''CREATE TABLE IF NOT EXISTS player_data (
    player_id TEXT PRIMARY KEY,
    birth_year INTEGER,
    birth_month INTEGER,
    birth_day INTEGER,
    birth_country TEXT,
    birth_state TEXT,
    birth_city TEXT,
    death_year INTEGER,
    death_month INTEGER,
    death_day INTEGER,
    death_country TEXT,
    death_state TEXT,
    death_city TEXT,
    name_first TEXT,
    name_last TEXT,
    name_given TEXT,
    weight INTEGER,
    height INTEGER,
    bats TEXT,
    throws TEXT,
    debut TEXT,
    final_game TEXT,
    retro_id TEXT,
    bbref_id TEXT
)''')

# Define insert query
insert_query = '''INSERT INTO player_data (player_id, birth_year, birth_month, birth_day, birth_country, birth_state, birth_city, death_year, death_month, death_day, death_country, death_state, death_city, name_first, name_last, name_given, weight, height, bats, throws, debut, final_game, retro_id, bbref_id)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)'''

# Open CSV file
with open(CSV_FILE, newline='', encoding='utf-8') as csvfile:
    reader = csv.reader(csvfile, delimiter=',')
    next(reader)  # Skip header row (if present)

    # Process each row
    for row in reader:
        # Replace empty strings with None
        row = [x if x else None for x in row]

        # Insert data and track success/failure
        if insert_player_data(row):
            inserted_rows += 1
        else:
            failed_rows += 1

# Commit changes and close connection
conn.commit()
conn.close()

print(f"Data import summary:")
print(f"- Inserted rows successfully: {inserted_rows}")
print(f"- Failed rows: {failed_rows}")
