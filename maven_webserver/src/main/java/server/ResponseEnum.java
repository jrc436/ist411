/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author drdea
 */
public enum ResponseEnum {
    OK,
    BAD,
    NotFound;
    public int errorCode() {
        switch (this) {
            case OK:
                return 200;
            case BAD:
                return 500;
            case NotFound:
                return 404;
        }
        throw new UnsupportedOperationException("unsupported response enum");
    }
    public String getHttpResponseLine() {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 ");
        sb.append(this.errorCode());
        sb.append(" ");
        switch (this) {
            case OK:
                sb.append("OK");
                break;
            case BAD:
                sb.append("BAD REQUEST");
                break;
            case NotFound:
                sb.append("Not Found");
                break;
        }
        return sb.toString();
    }
}
