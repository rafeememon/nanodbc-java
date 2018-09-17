#include "nanodbc_ext.h"

namespace nanodbc
{

void execute(result& result, connection& conn, const string& query, long timeout)
{
    result = execute(conn, query, 1, timeout);
}

void execute(result& result, statement& stmt)
{
    result = execute(stmt);
}

}
