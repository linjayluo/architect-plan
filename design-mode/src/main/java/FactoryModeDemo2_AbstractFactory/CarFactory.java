package FactoryModeDemo2_AbstractFactory;

public interface CarFactory {
	// 创建发动机
	Engine createEngine();
	// 创建座椅
	Chair createChair();
}