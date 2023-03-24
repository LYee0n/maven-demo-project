using System;
using Microsoft.Data.SqlClient;

// See https://aka.ms/new-console-template for more information
Console.WriteLine("Hello, World!");


// Define the connection string
string connectionString = "Server=localhost,1433;Database=master;User Id=sa;Password=YourStrong@Passw0rd;TrustServerCertificate=True;";

// Create a new SqlConnection object
using (SqlConnection connection = new SqlConnection(connectionString))
{
    try
    {
        // Open the database connection
        connection.Open();

        // Execute a sample query
        string query = "SELECT @@VERSION";
        using (SqlCommand command = new SqlCommand(query, connection))
        {
            using (SqlDataReader reader = command.ExecuteReader())
            {
                // Read the query results
                while (reader.Read())
                {
                    Console.WriteLine(reader.GetString(0));
                }
            }
        }
    }
    catch (Exception ex)
    {
        // Handle any errors
        Console.WriteLine("Error: " + ex.Message);
    }
}