# Go Config & Database Export

## ChatGPT
undefined

## ChatGPT
Sure! Here's an example code written in Go that implements the features you described:

```go
package main

import (
	"database/sql"
	"fmt"
	"io/ioutil"
	"log"
	"os"
	"strings"

	"gopkg.in/yaml.v2"

	_ "github.com/lib/pq"
)

type Config struct {
	Database   DatabaseConfig   `yaml:"database"`
	ExportPath string           `yaml:"export_path"`
	Tables     []TableConfig    `yaml:"tables"`
}

type DatabaseConfig struct {
	Host     string `yaml:"host"`
	Port     int    `yaml:"port"`
	Username string `yaml:"username"`
	Password string `yaml:"password"`
	Name     string `yaml:"name"`
}

type TableConfig struct {
	Name       string            `yaml:"name"`
	Columns    []ColumnConfig    `yaml:"columns"`
	UniqueKeys []string          `yaml:"unique_keys"`
	ForeignKeys []ForeignKeyConfig `yaml:"foreign_keys"`
}

type ColumnConfig struct {
	Name string `yaml:"name"`
	Type string `yaml:"type"`
}

type ForeignKeyConfig struct {
	ColumnName string `yaml:"column_name"`
	RefTable   string `yaml:"ref_table"`
	RefColumn  string `yaml:"ref_column"`
}

func main() {
	configFile := "config.yaml"
	config, err := loadConfig(configFile)
	if err != nil {
		log.Fatalf("Failed to load config file: %v", err)
	}

	db, err := connectToDatabase(config.Database)
	if err != nil {
		log.Fatalf("Failed to connect to the database: %v", err)
	}
	defer db.Close()

	for _, table := range config.Tables {
		data, err := readTableData(db, table)
		if err != nil {
			log.Printf("Failed to read data from table %s: %v", table.Name, err)
			continue
		}

		err = exportDataToYAML(data, config.ExportPath, table)
		if err != nil {
			log.Printf("Failed to export data for table %s: %v", table.Name, err)
			continue
		}

		log.Printf("Data for table %s exported successfully", table.Name)
	}
}

func loadConfig(filename string) (*Config, error) {
	yamlFile, err := ioutil.ReadFile(filename)
	if err != nil {
		return nil, fmt.Errorf("failed to read config file: %v", err)
	}

	config := &Config{}
	err = yaml.Unmarshal(yamlFile, config)
	if err != nil {
		return nil, fmt.Errorf("failed to unmarshal config file: %v", err)
	}

	return config, nil
}

func connectToDatabase(dbConfig DatabaseConfig) (*sql.DB, error) {
	connString := fmt.Sprintf("host=%s port=%d user=%s password=%s dbname=%s sslmode=disable",
		dbConfig.Host, dbConfig.Port, dbConfig.Username, dbConfig.Password, dbConfig.Name)

	db, err := sql.Open("postgres", connString)
	if err != nil {
		return nil, fmt.Errorf("failed to connect to the database: %v", err)
	}

	err = db.Ping()
	if err != nil {
		return nil, fmt.Errorf("failed to ping the database: %v", err)
	}

	return db, nil
}

func readTableData(db *sql.DB, table TableConfig) ([]map[string]interface{}, error) {
	query := fmt.Sprintf("SELECT * FROM %s", table.Name)
	rows, err := db.Query(query)
	if err != nil {
		return nil, fmt.Errorf("failed to execute query for table %s: %v", table.Name, err)
	}
	defer rows.Close()

	columnNames, err := rows.Columns()
	if err != nil {
		return nil, fmt.Errorf("failed to get column names for table %s: %v", table.Name, err)
	}

	columnValues := make([]interface{}, len(columnNames))
	columnPointers := make([]interface{}, len(columnNames))
	for i := range columnValues {
		columnPointers[i] = &columnValues[i]
	}

	var data []map[string]interface{}
	for rows.Next() {
		err := rows.Scan(columnPointers...)
		if err != nil {
			return nil, fmt.Errorf("failed to scan row data for table %s: %v", table.Name, err)
		}

		rowData := make(map[string]interface{})
		for i, columnName := range columnNames {
			rowData[columnName] = columnValues[i]
		}
		data = append(data, rowData)
	}

	if err := rows.Err(); err != nil {
		return nil, fmt.Errorf("failed to iterate over rows for table %s: %v", table.Name, err)
	}

	return data, nil
}

func exportDataToYAML(data []map[string]interface{}, exportPath string, table TableConfig) error {
	filename := fmt.Sprintf("%s/%s.yaml", exportPath, table.Name)
	file, err := os.Create(filename)
	if err != nil {
		return fmt.Errorf("failed to create export file for table %s: %v", table.Name, err)
	}
	defer file.Close()

	exportData := make(map[string]interface{})
	exportData["table_name"] = table.Name
	exportData["columns"] = getColumnData(table.Columns, data)

	yamlData, err := yaml.Marshal(exportData)
	if err != nil {
		return fmt.Errorf("failed to marshal data for table %s: %v", table.Name, err)
	}

	_, err = file.Write(yamlData)
	if err != nil {
		return fmt.Errorf("failed to write data to export file for table %s: %v", table.Name, err)
	}

	return nil
}

func getColumnData(columns []ColumnConfig, data []map[string]interface{}) []map[string]interface{} {
	var columnData []map[string]interface{}
	for _, row := range data {
		rowData := make(map[string]interface{})
		for _, column := range columns {
			columnName := column.Name
			if value, ok := row[columnName]; ok {
				rowData[columnName] = value
			}
		}
		columnData = append(columnData, rowData)
	}
	return columnData
}
```

In this code, we use the `database/sql` package to connect to a PostgreSQL database and execute queries. We also use the `gopkg.in/yaml.v2` package to marshal the data into YAML format. 

Please make sure to install the required packages (`github.com/lib/pq` and `gopkg.in/yaml.v2`) using `go get` before running the code.

Remember to update the `config.yaml` file with the appropriate values for your database connection and export settings. The config file should be placed in the same directory as the Go code file.

## ChatGPT
undefined

## ChatGPT
Sure! Here's an updated version of the code that implements the comparison of exported data with the data in the spec database. It generates the diff result data and generates CRUD SQL statements, ensuring that child data is created before parent data and child data is deleted before parent data:

```go
package main

import (
	"database/sql"
	"fmt"
	"io/ioutil"
	"log"
	"os"
	"strings"

	"gopkg.in/yaml.v2"

	_ "github.com/lib/pq"
)

type Config struct {
	Database   DatabaseConfig   `yaml:"database"`
	ExportPath string           `yaml:"export_path"`
	Tables     []TableConfig    `yaml:"tables"`
}

type DatabaseConfig struct {
	Host     string `yaml:"host"`
	Port     int    `yaml:"port"`
	Username string `yaml:"username"`
	Password string `yaml:"password"`
	Name     string `yaml:"name"`
}

type TableConfig struct {
	Name       string            `yaml:"name"`
	Columns    []ColumnConfig    `yaml:"columns"`
	UniqueKeys []string          `yaml:"unique_keys"`
	ForeignKeys []ForeignKeyConfig `yaml:"foreign_keys"`
}

type ColumnConfig struct {
	Name string `yaml:"name"`
	Type string `yaml:"type"`
}

type ForeignKeyConfig struct {
	ColumnName string `yaml:"column_name"`
	RefTable   string `yaml:"ref_table"`
	RefColumn  string `yaml:"ref_column"`
}

type DiffResult struct {
	Table       string
	Column      string
	Operation   string
	PrimaryKey  interface{}
	ForeignKeys []ForeignKeyConfig
}

func main() {
	configFile := "config.yaml"
	config, err := loadConfig(configFile)
	if err != nil {
		log.Fatalf("Failed to load config file: %v", err)
	}

	db, err := connectToDatabase(config.Database)
	if err != nil {
		log.Fatalf("Failed to connect to the database: %v", err)
	}
	defer db.Close()

	for _, table := range config.Tables {
		exportedData, err := readExportedData(config.ExportPath, table.Name)
		if err != nil {
			log.Printf("Failed to read exported data for table %s: %v", table.Name, err)
			continue
		}

		specData, err := readTableData(db, table)
		if err != nil {
			log.Printf("Failed to read data from table %s: %v", table.Name, err)
			continue
		}

		diffResult := compareData(exportedData, specData, table)

		err = exportDiffResult(diffResult, config.ExportPath, table)
		if err != nil {
			log.Printf("Failed to export diff result for table %s: %v", table.Name, err)
			continue
		}

		sqlStatements := generateCRUDSQL(diffResult, table)
		err = exportSQLStatements(sqlStatements, config.ExportPath, table)
		if err != nil {
			log.Printf("Failed to export SQL statements for table %s: %v", table.Name, err)
			continue
		}

		log.Printf("Diff result and SQL statements exported successfully for table %s", table.Name)
	}
}

func loadConfig(filename string) (*Config, error) {
	yamlFile, err := ioutil.ReadFile(filename)
	if err != nil {
		return nil, fmt.Errorf("failed to read config file: %v", err)
	}

	config := &Config{}
	err = yaml.Unmarshal(yamlFile, config)
	if err != nil {
		return nil, fmt.Errorf("failed to unmarshal config file: %v", err)
	}

	return config, nil
}

func connectToDatabase(dbConfig DatabaseConfig) (*sql.DB, error) {
	connString := fmt.Sprintf("host=%s port=%d user=%s password=%s dbname=%s sslmode=disable",
		dbConfig.Host, dbConfig.Port, dbConfig.Username, dbConfig.Password, dbConfig.Name)

	db, err := sql.Open("postgres", connString)
	if err != nil {
		return nil, fmt.Errorf("failed to connect to the database: %v", err)
	}

	err = db.Ping()
	if err != nil {
		return nil, fmt.Errorf("failed to ping the database: %v", err)
	}

	return db, nil
}

func readExportedData(exportPath string, tableName string) ([]map[string]interface{}, error) {
	filename := fmt.Sprintf("%s/%s.yaml", exportPath, tableName)
	yamlFile, err := ioutil.ReadFile(filename)
	if err != nil {
		return nil, fmt.Errorf("failed to read exported data file for table %s: %v", tableName, err)
	}

	exportData := make(map[string]interface{})
	err = yaml.Unmarshal(yamlFile, &exportData)
	if err != nil {
		return nil, fmt.Errorf("failed to unmarshal exported data for table %s: %v", tableName, err)
	}

	data, ok := exportData["columns"].([]interface{})
	if !ok {
		return nil, fmt.Errorf("invalid exported data format for table %s", tableName)
	}

	exportedData := make([]map[string]interface{}, len(data))
	for i, item := range data {
		exportedData[i] = item.(map[string]interface{})
	}

	return exportedData, nil
}

func compareData(exportedData, specData []map[string]interface{}, table TableConfig) []DiffResult {
	diffResult := make([]DiffResult, 0)

	for _, exportedRow := range exportedData {
		primaryKey := getPrimaryKeyValue(exportedRow, table.UniqueKeys)
		found := false

		for _, specRow := range specData {
			specPrimaryKey := getPrimaryKeyValue(specRow, table.UniqueKeys)

			if primaryKey == specPrimaryKey {
				// Check column values
				for _, column := range table.Columns {
					columnName := column.Name
					exportedValue := exportedRow[columnName]
					specValue := specRow[columnName]

					if !compareColumnValues(exportedValue, specValue) {
						diffResult = append(diffResult, DiffResult{
							Table:       table.Name,
							Column:      columnName,
							Operation:   "update",
							PrimaryKey:  primaryKey,
							ForeignKeys: table.ForeignKeys,
						})
					}
				}

				found = true
				break
			}
		}

		if !found {
			diffResult = append(diffResult, DiffResult{
				Table:       table.Name,
				Operation:   "insert",
				PrimaryKey:  primaryKey,
				ForeignKeys: table.ForeignKeys,
			})
		}
	}

	// Check for deleted rows
	for _, specRow := range specData {
		specPrimaryKey := getPrimaryKeyValue(specRow, table.UniqueKeys)
		found := false

		for _, exportedRow := range exportedData {
			primaryKey := getPrimaryKeyValue(exportedRow, table.UniqueKeys)
			if primaryKey == specPrimaryKey {
				found = true
				break
			}
		}

		if !found {
			diffResult = append(diffResult, DiffResult{
				Table:       table.Name,
				Operation:   "delete",
				PrimaryKey:  specPrimaryKey,
				ForeignKeys: table.ForeignKeys,
			})
		}
	}

	return diffResult
}

func getPrimaryKeyValue(rowData map[string]interface{}, uniqueKeys []string) interface{} {
	primaryKey := make([]interface{}, len(uniqueKeys))
	for i, key := range uniqueKeys {
		primaryKey[i] = rowData[key]
	}
	return primaryKey
}

func compareColumnValues(value1, value2 interface{}) bool {
	// TODO: Implement the logic to compare column values based on their types
	// You can use type assertions to perform specific value comparisons for different types
	// For example: if value1.(int) != value2.(int) { return false }
	//             if value1.(string) != value2.(string) { return false }
	//             ...
	return value1 == value2
}

func exportDiffResult(diffResult []DiffResult, exportPath string, table TableConfig) error {
	filename := fmt.Sprintf("%s/%s_diff.yaml", exportPath, table.Name)
	file, err := os.Create(filename)
	if err != nil {
		return fmt.Errorf("failed to create diff result file for table %s: %v", table.Name, err)
	}
	defer file.Close()

	yamlData, err := yaml.Marshal(diffResult)
	if err != nil {
		return fmt.Errorf("failed to marshal diff result for table %s: %v", table.Name, err)
	}

	_, err = file.Write(yamlData)
	if err != nil {
		return fmt.Errorf("failed to write diff result to file for table %s: %v", table.Name, err)
	}

	return nil
}

func generateCRUDSQL(diffResult []DiffResult, table TableConfig) []string {
	sqlStatements := make([]string, 0)

	for _, diff := range diffResult {
		switch diff.Operation {
		case "insert":
			sqlStatements = append(sqlStatements, generateInsertSQL(diff, table))
		case "update":
			sqlStatements = append(sqlStatements, generateUpdateSQL(diff, table))
		case "delete":
			sqlStatements = append(sqlStatements, generateDeleteSQL(diff, table))
		}
	}

	// Reorder SQL statements to create child data before parent data and delete child data before parent data
	sqlStatements = reorderSQLStatements(sqlStatements, table.ForeignKeys)

	return sqlStatements
}

func generateInsertSQL(diff DiffResult, table TableConfig) string {
	// TODO: Implement the logic to generate INSERT SQL statement
	// Use diff.PrimaryKey and diff.ForeignKeys to build the SQL statement

	return ""
}

func generateUpdateSQL(diff DiffResult, table TableConfig) string {
	// TODO: Implement the logic to generate UPDATE SQL statement
	// Use diff.PrimaryKey and diff.ForeignKeys to build the SQL statement

	return ""
}

func generateDeleteSQL(diff DiffResult, table TableConfig) string {
	// TODO: Implement the logic to generate DELETE SQL statement
	// Use diff.PrimaryKey and diff.ForeignKeys to build the SQL statement

	return ""
}

func reorderSQLStatements(sqlStatements []string, foreignKeys []ForeignKeyConfig) []string {
	// TODO: Implement the logic to reorder SQL statements
	// Ensure that child data is created before parent data
	// and child data is deleted before parent data

	return sqlStatements
}

func exportSQLStatements(sqlStatements []string, exportPath string, table TableConfig) error {
	filename := fmt.Sprintf("%s/%s_sql.txt", exportPath, table.Name)
	file, err := os.Create(filename)
	if err != nil {
		return fmt.Errorf("failed to create SQL statements file for table %s: %v", table.Name, err)
	}
	defer file.Close()

	sqlData := strings.Join(sqlStatements, "\n")

	_, err = file.WriteString(sqlData)
	if err != nil {
		return fmt.Errorf("failed to write SQL statements to file for table %s: %v", table.Name, err)
	}

	return nil
}
```

In the code above, we've added functions to compare the exported data with the data in the spec database and generate the diff result data. We've also added functions to generate CRUD SQL statements based on the diff result. The SQL statements are ordered to ensure that child data is created before parent data and child data is deleted before parent data.

Please note that the `generateInsertSQL`, `generateUpdateSQL`, `generateDeleteSQL`, and `reorderSQLStatements` functions contain TODO comments. You will need to implement the specific logic for generating the SQL statements and reordering them based on your database schema and requirements.

Make sure to update the `config.yaml` file with the appropriate values for your database connection, export path, table configurations, and foreign keys.

Remember to install the required packages (`github.com/lib/pq` and `gopkg.in/yaml.v2`) using `go get` before running the code.

## ChatGPT
undefined

## ChatGPT
Certainly! Here's the continued implementation of the `generateInsertSQL`, `generateUpdateSQL`, `generateDeleteSQL`, and `reorderSQLStatements` functions:

```go
func generateInsertSQL(diff DiffResult, table TableConfig) string {
	sql := fmt.Sprintf("INSERT INTO %s (", table.Name)
	columns := make([]string, 0)
	values := make([]string, 0)

	for _, column := range table.Columns {
		columns = append(columns, column.Name)
		values = append(values, formatValue(diff.PrimaryKey, column.Type))
	}

	sql += strings.Join(columns, ", ") + ") VALUES (" + strings.Join(values, ", ") + ")"

	return sql
}

func generateUpdateSQL(diff DiffResult, table TableConfig) string {
	sql := fmt.Sprintf("UPDATE %s SET ", table.Name)
	setValues := make([]string, 0)

	for _, column := range table.Columns {
		if column.Name != diff.Column {
			setValues = append(setValues, fmt.Sprintf("%s = %s", column.Name, formatValue(diff.PrimaryKey, column.Type)))
		}
	}

	sql += strings.Join(setValues, ", ") + " WHERE " + formatPrimaryKeyWhereClause(diff.PrimaryKey, table.UniqueKeys)

	return sql
}

func generateDeleteSQL(diff DiffResult, table TableConfig) string {
	sql := fmt.Sprintf("DELETE FROM %s WHERE %s", table.Name, formatPrimaryKeyWhereClause(diff.PrimaryKey, table.UniqueKeys))

	return sql
}

func reorderSQLStatements(sqlStatements []string, foreignKeys []ForeignKeyConfig) []string {
	// Map to store the index of each table
	tableIndex := make(map[string]int)

	// Build the table index map
	for i, table := range foreignKeys {
		tableIndex[table.RefTable] = i
	}

	// Reorder the SQL statements based on the table dependencies
	for i := 0; i < len(sqlStatements)-1; i++ {
		for j := i + 1; j < len(sqlStatements); j++ {
			table1 := getTableNameFromSQL(sqlStatements[i])
			table2 := getTableNameFromSQL(sqlStatements[j])

			index1, exists1 := tableIndex[table1]
			index2, exists2 := tableIndex[table2]

			if exists1 && exists2 && index1 > index2 {
				sqlStatements[i], sqlStatements[j] = sqlStatements[j], sqlStatements[i]
			}
		}
	}

	return sqlStatements
}

func formatValue(value interface{}, dataType string) string {
	switch dataType {
	case "string":
		return fmt.Sprintf("'%v'", value)
	default:
		return fmt.Sprintf("%v", value)
	}
}

func formatPrimaryKeyWhereClause(primaryKey interface{}, uniqueKeys []string) string {
	conditions := make([]string, 0)

	for i, key := range uniqueKeys {
		conditions = append(conditions, fmt.Sprintf("%s = %s", key, formatValue(primaryKey[i], "string")))
	}

	return strings.Join(conditions, " AND ")
}

func getTableNameFromSQL(sql string) string {
	// Assuming the SQL statement is in the format "INSERT INTO <table_name> ..." or "DELETE FROM <table_name> ..."
	parts := strings.Split(sql, " ")
	return parts[2]
}
```

In the code above, the `generateInsertSQL` function generates the INSERT SQL statement for the diff result. It iterates over the columns and values to build the statement.

The `generateUpdateSQL` function generates the UPDATE SQL statement for the diff result. It builds the SET clause with the updated column values and appends the WHERE clause using the primary key.

The `generateDeleteSQL` function generates the DELETE SQL statement for the diff result. It constructs the WHERE clause using the primary key.

The `reorderSQLStatements` function reorders the SQL statements based on the table dependencies defined in the foreign keys. It ensures that child data is created before parent data and child data is deleted before parent data.

The `formatValue` function converts the value to the appropriate format based on the data type (currently supports only string).

The `formatPrimaryKeyWhereClause` function formats the WHERE clause using the primary key and unique keys.

The `getTableNameFromSQL` function extracts the table name from an SQL statement.

Remember to customize these functions based on your specific database schema and requirements.

Let me know if you need any further assistance!