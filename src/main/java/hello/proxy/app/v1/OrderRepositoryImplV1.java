package hello.proxy.app.v1;

public class OrderRepositoryImplV1 implements OrderRepositoryV1 {

    @Override
    public void save(String itemId) {

        // 저장 로직
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생!");
        }
        sleep(1000);
    }

    private void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
