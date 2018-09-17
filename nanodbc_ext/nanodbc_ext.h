#include <nanodbc/nanodbc.h>

namespace nanodbc
{

void execute(
    result& result,
    connection& conn,
    const string& query,
    long timeout = 0);

void execute(
    result& result,
    statement& stmt);

}
