#include "nanodbc_ext.h"

namespace nanodbc
{

void execute(result& result, connection& conn, const string& query, long batch_operations, long timeout)
{
    result = execute(conn, query, batch_operations, timeout);
}

void execute(result& result, statement& stmt, long batch_operations)
{
    result = execute(stmt, batch_operations);
}

}