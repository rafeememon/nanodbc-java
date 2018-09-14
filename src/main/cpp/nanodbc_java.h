#include <nanodbc/nanodbc.h>

namespace nanodbc
{

void execute(
    result& result,
    connection& conn,
    const string& query,
    long batch_operations = 1,
    long timeout = 0);

void execute(
    result& result,
    statement& stmt,
    long batch_operations = 1);

}
